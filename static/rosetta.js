  function retrieve_data() {
    const myInit = {
      method: 'POST',
      headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
      },
      body: 'python '+ document.querySelector('.textEditorArea').value,
      cache: 'default'
    };

    fetch('rosetta', myInit).
      then(
        function (response) {
           return response.body.getReader().read()
          }
      ).then(
        function (data) {
            const decoded_value = new TextDecoder().decode(data.value);
            document.getElementById('plotContent').innerHTML = decoded_value;
        }
      );
  }
