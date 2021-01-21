var downloadTextFile = {
  "message0": "download a text file %1",
  "args0": [
    {
     "type": "field_variable",
     "name": "VAR",
     "variable": "item",
    }
  ],
  "type": "input_value",
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