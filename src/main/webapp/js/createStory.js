function init() {
	const $ = go.GraphObject.make;
	
	// Paleta de colores a utilizar para los elementos
	var purple = '#5114d3';
	var blue = '#00A6ED';
	var red = '#ff0000';
	var white = '#FFF'
	
	var selectColor = '#00A6ED';
	var lineColor = '#9a73ee';
	
	// Estilización del texto en los eventos
	function textStyle() {
		return {
			margin: 10,
			wrap: go.Wrap.Fit,
			textAlign: 'center',
			editable: false,
			stroke: white,
		};
	}
	
	storyDiagram = new go.Diagram ('storyDiagram', {
		'toolManager.mouseWheelBehavior': go.WheelMode.Zoom,
		initialAutoScale: go.AutoScale.Uniform,
		'linkingTool.direction': go.LinkingDirection.ForwardsOnly,
		layout: $ (go.LayeredDigraphLayout, {
			isInitial: false,
			isOngoing: false,
			layerSpacing: 100,
		}),
		'undoManager.isEnabled': true,
	});
	
	// Escuchar el evento de doble clic en el nodo
	storyDiagram.nodeTemplate =
		$ (go.Node, 'Auto',
			{
				doubleClick: openEditModal // Abrir modal al hacer doble clic
			},
			new go.Binding ('location', 'loc', go.Point.parse).makeTwoWay (go.Point.stringify),
			$ (go.Shape, 'RoundedRectangle',
				{
					fill: 'white',
					stroke: 'rgba(0,0,0,0)',
					portId: '',
					fromLinkable: true,
					toLinkable: true,
					cursor: 'pointer',
					toEndSegmentLength: 50,
					fromEndSegmentLength: 40
				},
				new go.Binding ('fill', 'color')
			),
			$ (go.TextBlock, 'Evento', textStyle (),
				new go.Binding ('text', 'text').makeTwoWay ())
		);
	
	storyDiagram.commandHandler.deleteSelection = function () {
		var diagram = this.diagram;
		var selectedParts = diagram.selection;
		var nodesToDelete = [];
		selectedParts.each (function (part) {
			if (part instanceof go.Node && part.data.category === 'startEvent') {
				alert ('El nodo de inicio no se puede eliminar.');
				return;
			}
			nodesToDelete.push (part);
		});
		
		// Solo eliminar si hay nodos a posibles
		if (nodesToDelete.length > 0) {
			diagram.startTransaction ('Delete Selection');
			nodesToDelete.forEach (function (part) {
				diagram.remove (part);
			});
			diagram.commitTransaction ('Delete Selection');
		}
	};
	
	// Actualiza estilos para representar que hay cambios sin guardar
	// !NOTA! "e" no usado eliminar mas adelante si no lo necesitamo
	storyDiagram.addDiagramListener ('Modified', (e) => {
		var button = document.getElementById ('SaveButton');
		if (button) button.disabled = !storyDiagram.isModified;
		var idx = document.title.indexOf ('*');
		if (storyDiagram.isModified) {
			if (idx < 0) document.title += '*';
			button.style.backgroundColor = '#5100ff';
		} else {
			if (idx >= 0) document.title = document.title.slice (0, idx);
			button.style.backgroundColor = '#945fff';
		}
	});
	
	var defaultAdornment =
		$ (go.Adornment, 'Spot',
			$ (go.Panel, 'Auto',
				$ (go.Shape, {fill: null, stroke: selectColor, strokeWidth: 3}),
				$ (go.Placeholder)
			),
			$ ('Button', {
					alignment: new go.Spot (1, 0, -5, 5),
					click: addNodeAndLink,
				},
				new go.Binding ('visible', '', (a) => !a.diagram.isReadOnly).ofObject (),
				$ (go.Shape, 'PlusLine',
					{desiredSize: new go.Size (6, 6), stroke: '#9568f8'} // Cambiar color del ícono a blanco
				)
			)
		);
	
	storyDiagram.nodeTemplateMap.add (
		'middleEvent',
		$ (go.Node, 'Auto',
			{
				selectionAdornmentTemplate: defaultAdornment,
				isShadowed: false,
				doubleClick: openEditModal // Abrir modal al hacer doble clic
			},
			new go.Binding ('location', 'loc', go.Point.parse).makeTwoWay (go.Point.stringify),
			$ (go.Shape,
				'RoundedRectangle',
				{
					fill: purple,
					stroke: lineColor,
					portId: '',
					fromLinkable: true,
					toLinkable: true,
					cursor: 'pointer',
					toEndSegmentLength: 50,
					fromEndSegmentLength: 40,
				},
				new go.Binding ('fill', 'color')
			),
			$ (go.TextBlock, 'Evento', textStyle (), new go.Binding ('text', 'text').makeTwoWay ())
		)
	);
	
	storyDiagram.nodeTemplateMap.add (
		'startEvent',
		$ (go.Node, 'Auto',
			{
				isShadowed: false,
				doubleClick: openEditModal // Abrir modal al hacer doble clic
			},
			new go.Binding ('location', 'loc', go.Point.parse).makeTwoWay (go.Point.stringify),
			$ (go.Shape,
				'RoundedRectangle',
				{
					name: 'SHAPE',
					fill: blue,
					stroke: lineColor,
					portId: '',
					fromLinkable: true,
					toLinkable: false,
					cursor: 'pointer',
					toEndSegmentLength: 50,
					fromEndSegmentLength: 40,
				},
				new go.Binding ('fill', 'color')
			),
			$ (go.TextBlock, 'Inicio', textStyle (), new go.Binding ('text', 'text').makeTwoWay ())
		)
	);
	
	storyDiagram.nodeTemplateMap.add (
		'endEvent',
		$ (go.Node, 'Auto',
			{
				isShadowed: false,
				doubleClick: openEditModal // Abrir modal al hacer doble clic
			},
			new go.Binding ('location', 'loc', go.Point.parse).makeTwoWay (go.Point.stringify),
			$ (go.Shape, 'RoundedRectangle', {
				fill: red,
				stroke: lineColor,
				portId: '',
				toLinkable: true,
				toEndSegmentLength: 50
			}, new go.Binding ('fill', 'color')),
			$ (go.TextBlock, 'Final', textStyle (), new go.Binding ('text', 'text').makeTwoWay ())
		)
	);
	
	// click en el botón de un nodo middleEvent inserta un nuevo nodo a la derecha del nodo seleccionado
	// y agrega un enlace a ese nuevo nodo
	function addNodeAndLink(e, obj) {
		var adorn = obj.part;
		if (adorn === null) return;
		e.handled = true;
		var diagram = adorn.diagram;
		diagram.startTransaction ('Add State');
		var fromNode = adorn.adornedPart;
		var fromData = fromNode.data;
		
		// Verificar la cantidad de conexiones salientes del nodo para evitar +3 conexiones
		var outgoingLinks = fromNode.findLinksOutOf ().count;
		if (outgoingLinks >= 2) {
			alert ('El evento no puede tener más de dos conexiones salientes.');
			diagram.commitTransaction ('Add State');
			return;
		}
		
		// Crear un nuevo objeto de datos "State", posicionado a la derecha del nodo middleEvent
		var toData = {category: 'middleEvent'};
		var p = fromNode.location;
		toData.loc = p.x + 200 + ' ' + p.y; // la propiedad "loc" es un string, no un objeto Point
		// Agregar los nuevos datos del nodo al modelo
		var model = diagram.model;
		model.addNodeData (toData);
		// Crear un enlace de datos desde los datos del nodo antiguo a los datos del nuevo nodo
		var linkdata = {};
		linkdata[model.linkFromKeyProperty] = model.getKeyForNodeData (fromData);
		linkdata[model.linkToKeyProperty] = model.getKeyForNodeData (toData);
		// Agregar los datos del enlace al modelo
		model.addLinkData (linkdata);
		// Al crearlo dejar el selector pocisionado en el nuevo nodo
		var newnode = diagram.findNodeForData (toData);
		diagram.select (newnode);
		diagram.commitTransaction ('Add State');
	}
	// Template de el trazado de los nodos y sus conexiones
	storyDiagram.linkTemplate = $ (go.Link,
		{curve: go.Curve.Bezier, toShortLength: 5, fromSpot: go.Spot.Right, toSpot: go.Spot.Left},
		$ (go.Shape,
			{stroke: lineColor, strokeWidth: 2.5}
		),
		$ (go.Shape,
			{toArrow: 'Kite', fill: lineColor, stroke: lineColor, scale: 1.5}
		)
	);
	
	// Cuando se crea un nuevo enlace, determinar qué plantilla usar
	storyDiagram.addDiagramListener ('LinkDrawn', (e) => {
		var link = e.subject;
		if (link.fromNode.findLinksOutOf ().count > 2) {
			storyDiagram.remove (link);
			alert ('El evento no puede tener más de dos conexiones salientes.');
		} else if (link.toNode.findLinksInto ().count > 2) {
			storyDiagram.remove (link);
			alert ('El evento no puede tener más de dos conexiones entrantes.');
		}
	});
	
	var buttonsBar = new go.Palette (
		'buttonsBar', // Crear una nueva Paleta en el elemento HTML DIV
		{
			// Compartir el mapa de plantillas con la Paleta
			nodeTemplateMap: storyDiagram.nodeTemplateMap,
			autoScale: go.AutoScale.Uniform, // Responsividad del GoJS
		}
	);
	
	buttonsBar.model.nodeDataArray = [
		{category: 'middleEvent'},
		{category: 'endEvent'},
	];
	
	
	// Leer los datos en formato JSON del elemento "mySavedModel" cargados apartir del JAVA
	load ();
	layout ();
}

// Función para abrir el modal
function openEditModal(e, obj) {
	var node = obj.part;
	if ( !node) return;
	
	var data = node.data;
	
	// Cargar los datos del nodo en el formulario
	document.getElementById ('nodeText').value = data.text || '';
	document.getElementById ('nodeDescription').value = data.description || '';
	document.getElementById ('fileUpload').value = ''; // Limpiar archivo cargado
	
	// Limpiar las vistas previas
	clearPreviews ();
	
	// Actualizar vistas previas si ya hay archivos cargados
	if (data.hasImage) {
		document.getElementById ('imagePreviewContainer').classList.add ('active');
	}
	if (data.hasAudio) {
		document.getElementById ('audioPreviewContainer').classList.add ('active');
	}
	if (data.hasVideo) {
		document.getElementById ('videoPreviewContainer').classList.add ('active');
	}
	
	// Mostrar el área de arrastre si no hay combinación de imagen y audio
	if ( !(data.hasImage && data.hasAudio) && !data.hasVideo) {
		document.getElementById ('dropArea').style.display = 'block';
	} else {
		document.getElementById ('dropArea').style.display = 'none';
	}
	
	// Mostrar el modal
	document.getElementById ('editModal').style.display = 'flex';
	
	// Guardar los datos del nodo en el modal
	window.currentNode = node; // Guardar referencia al nodo actual
}

// Función para cerrar el modal
function closeModal() {
	document.getElementById ('editModal').style.display = 'none';
}

// Función para guardar los datos del nodo
function saveNodeData() {
	var node = window.currentNode;
	if ( !node) return;
	
	var text = document.getElementById ('nodeText').value;
	var description = document.getElementById ('nodeDescription').value;
	
	// Actualizar los datos del nodo
	storyDiagram.model.setDataProperty (node.data, 'text', text);
	storyDiagram.model.setDataProperty (node.data, 'description', description);
	
	// Actualizar datos del nodo con los archivos
	var hasImage = document.getElementById ('imagePreviewContainer').classList.contains ('active');
	var hasAudio = document.getElementById ('audioPreviewContainer').classList.contains ('active');
	var hasVideo = document.getElementById ('videoPreviewContainer').classList.contains ('active');
	
	storyDiagram.model.setDataProperty (node.data, 'hasImage', hasImage);
	storyDiagram.model.setDataProperty (node.data, 'hasAudio', hasAudio);
	storyDiagram.model.setDataProperty (node.data, 'hasVideo', hasVideo);

	
	// Cerrar el modal
	closeModal ();
}

// Configuración de arrastrar y soltar
document.getElementById ('dropArea').addEventListener ('dragover', function (event) {
	event.preventDefault ();
	event.stopPropagation ();
	event.dataTransfer.dropEffect = 'copy';
	this.classList.add ('dragover');
});

document.getElementById ('dropArea').addEventListener ('dragleave', function (event) {
	event.preventDefault ();
	event.stopPropagation ();
	this.classList.remove ('dragover');
});

document.getElementById ('dropArea').addEventListener ('drop', function (event) {
	event.preventDefault ();
	event.stopPropagation ();
	this.classList.remove ('dragover');
	
	var files = event.dataTransfer.files;
	handleFiles (files);
});

document.getElementById ('dropArea').addEventListener ('click', function () {
	document.getElementById ('fileUpload').click ();
});

document.getElementById ('fileUpload').addEventListener ('change', function (event) {
	var files = event.target.files;
	handleFiles (files);
});

// Función para manejar los archivos
function handleFiles(files) {
	let hasImage = document.getElementById ('imagePreviewContainer').classList.contains ('active');
	let hasAudio = document.getElementById ('audioPreviewContainer').classList.contains ('active');
	let hasVideo = document.getElementById ('videoPreviewContainer').classList.contains ('active');
	
	for (let file of files) {
		if (file.type.startsWith ('image/')) {
			if (hasVideo) {
				alert ('No se permite subir imagen junto con video.');
				return;
			}
			hasImage = true;
			document.getElementById ('imagePreviewContainer').classList.add ('active');
		} else if (file.type.startsWith ('audio/')) {
			if (hasVideo) {
				alert ('No se permite subir audio junto con video.');
				return;
			}
			hasAudio = true;
			document.getElementById ('audioPreviewContainer').classList.add ('active');
		} else if (file.type.startsWith ('video/')) {
			if (hasImage || hasAudio) {
				alert ('No se permite subir video junto con imagen o audio.');
				return;
			}
			hasVideo = true;
			document.getElementById ('videoPreviewContainer').classList.add ('active');
		}
	}
	
	// Mostrar u ocultar el área de arrastre según las combinaciones permitidas
	if (hasImage && hasAudio) {
		document.getElementById ('dropArea').style.display = 'none';
	} else {
		document.getElementById ('dropArea').style.display = 'block';
	}
}

// Función para limpiar las vistas previas
function clearPreviews() {
	document.getElementById ('imagePreviewContainer').classList.remove ('active');
	document.getElementById ('audioPreviewContainer').classList.remove ('active');
	document.getElementById ('videoPreviewContainer').classList.remove ('active');
}

// Función de guardar el diagrama en formato JSON
function saveStory() {
	document.getElementById ('mySavedModel').value = storyDiagram.model.toJson ();
	storyDiagram.isModified = false;

	const storyTitle = document.getElementById("storyTitle").value;
	const storyData = storyDiagram.model.toJson();

	// Crear el objeto de datos que se enviará al servlet
	const dataToSend = {
		title: storyTitle,
		diagram: storyData
	};

	// Hacer la solicitud AJAX al servlet
	fetch('createStory', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(dataToSend)
	})
		.then(response => response.json())  // Esperar una respuesta JSON del servlet
		.then(data => {
			// Procesar la respuesta del servlet
			console.log("Respuesta del servidor:", data);
		})
		.catch(error => {
			console.error("Error al guardar la historia:", error);
		});



	console.log (storyDiagram.model.toJson ());
}

// Función para cargar el modelo guardado en JSON
function load() {
	storyDiagram.model = go.Model.fromJson (document.getElementById ('mySavedModel').value);
}

function layout() {
	storyDiagram.layoutDiagram (true);
}

const storyTitleLabel = document.getElementById("storyTitle")
storyTitleLabel.addEventListener("keydown", function (e) {
	if (this.value.length >= 20 && e.key !== "Backspace") {
		e.preventDefault();
	}
})


window.addEventListener ('DOMContentLoaded', init);