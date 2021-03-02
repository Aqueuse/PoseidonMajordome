var options = {
    toolbox: toolbox,
    collapse: true,
    comments: true,
    disable: false,
    maxBlocks: Infinity,
    trashcan: false,
    horizontalLayout: false,
    toolboxPosition: 'start',
    css: true,
    media: 'https://blockly-demo.appspot.com/static/media/',
    rtl: false,
    scrollbars: true,
    sounds: true,
    oneBasedIndex: true,
    zoom: {
      controls: true,
      wheel: true,
      startScale: 1,
      maxScale: 3,
      minScale: 0.3,
      scaleSpeed: 1.2
    }
  };

  var workspace = Blockly.inject(blocklyDiv, options);
