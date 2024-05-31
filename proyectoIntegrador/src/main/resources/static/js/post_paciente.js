window.addEventListener('load', function () {

    const formulario = document.querySelector('#add_new_paciente');

    // Ante un submit del formulario se ejecutará la siguiente función
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        // Creamos un JSON que tendrá los datos del nuevo paciente
        const formData = {
            dni: document.querySelector('#dni').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value
        };

        // Invocamos utilizando la función fetch la API paciente con el método POST que guardará
        // Enviaremos en formato JSON
        const url = '/pacientes/registrar';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                // Si no hay ningún error se muestra un mensaje diciendo que el paciente
                // se agregó bien
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Paciente Agregado</strong></div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
            .catch(error => {
                // Si hay algún error se muestra un mensaje diciendo que el paciente
                // no se pudo guardar y se intente nuevamente
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Error, intente nuevamente</strong></div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                // Se dejan todos los campos vacíos por si se quiere ingresar otro paciente
                resetUploadForm();
            })
    });

    function resetUploadForm(){
        document.querySelector('#dni').value = "";
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").classList.add("active");
        } else if (pathname == "/get_pacientes.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});
