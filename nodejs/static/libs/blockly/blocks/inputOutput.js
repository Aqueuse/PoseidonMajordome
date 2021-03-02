goog.require('Blockly.FieldVariable');
goog.require('Blockly.FieldTextInput');

var downloadTextFile = {
  "message0": "from web get %1",
  "args0": [
    {
     "type": "field_input",
     "name": "VAR",
     "text": "http://example.com/data.csv",
     "variable": "item",
    }
  ],
  "type": "field_input",
  "name": "url",
  "output": "String",
  "colour": 230
};

Blockly.Blocks['input_downloadTextFile'] = {
  init: function() {
    this.jsonInit(downloadTextFile);
    // Assign 'this' to a variable for use in the tooltip closure below.
    var thisBlock = this;
    this.setTooltip(function() {
      return "hello blockly";
    });
  }
};

var loadTextFile = {
  "message0": "from local get %1",
  "args0": [
    {
     "type": "field_input",
     "name": "VAR",
     "text": "file://data.csv",
     "variable": "item",
    }
  ],
  "type": "field_input",
  "name": "path",
  "output": "String",
  "colour": 230
};

Blockly.Blocks['input_loadTextFile'] = {
  init: function() {
    this.jsonInit(loadTextFile);
    // Assign 'this' to a variable for use in the tooltip closure below.
    var thisBlock = this;
    this.setTooltip(function() {
      return "hello blockly";
    });
  }
};

var textFileWrite = {
  "message0": "on text file save %1",
  "args0": [
    {
     "type": "field_input",
     "name": "VAR",
     "text": "file://myOutputFolder/data.csv",
     "variable": "item",
    }
  ],
  "type": "field_input",
  "name": "localPath",
  "output": "String",
  "colour": 230
};

Blockly.Blocks['output_writeTextFile'] = {
  init: function() {
    this.jsonInit(textFileWrite);
    // Assign 'this' to a variable for use in the tooltip closure below.
    var thisBlock = this;
    this.setTooltip(function() {
      return "hello blockly";
    });
  }
};

var imgFileWrite = {
  "message0": "on %1 save %2",
  "args0": [
    {
      "type": "field_dropdown",
      "name": "FileType",
      "options": [
        ["PNG file", "PNG"],
        ["SVG file", "SVG"]
      ]
    },
    {
     "type": "field_input",
     "name": "VAR",
     "text": "file://myOutputFolder/data.extension",
     "variable": "item",
    }
  ],
  "type": "field_input",
  "name": "localImg",
  "output": "String",
  "colour": 230
};

Blockly.Blocks['output_writeImgFile'] = {
  init: function() {
    this.jsonInit(imgFileWrite);
    // Assign 'this' to a variable for use in the tooltip closure below.
    var thisBlock = this;
    this.setTooltip(function() {
      return "hello blockly";
    });
  }
};