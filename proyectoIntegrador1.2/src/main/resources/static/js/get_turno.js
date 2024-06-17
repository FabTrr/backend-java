window.addEventListener('load', function () {
    (function(){

        // Con fetch invocamos a la API para obtener los turnos con el método GET
        // Nos devolverá un JSON con una colección
        const url = '/turnos';
        const settings = {
            method: 'GET'
        }

        fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            for (turno of data) {
                var table = document.getElementById("turnoTable");
                var turnoRow = table.insertRow();
                let tr_id = 'tr_' + turno.id;
                turnoRow.id = tr_id;

                let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                      ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                                      'Eliminar' +
                                      '</button>';

                let infoButton = '<span>' +
                                 turno.id +
                                 '</span>';

                let updateButton = '<a' +
                                   ' href="update_turno.html?id=' + turno.id + '"' +
                                   ' class="btn btn-warning">' +
                                   'Actualizar' +
                                   '</a>';

                turnoRow.innerHTML = '<td>' + infoButton + '</td>' +
                    '<td class="td_paciente_id">' + turno.paciente.id + '</td>' +
                    '<td class="td_odontologo_id">' + turno.odontologo.id + '</td>' +
                    '<td class="td_fecha">' + turno.fecha + ' ' + turno.hora + '</td>' +
                    '<td>' + updateButton + '</td>' +
                    '<td>' + deleteButton + '</td>';

            }
        })
    })();

    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/get_turnos.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});

