window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de pacientes con el método GET
      //nos devolverá un JSON con una colección de pacientes
      const url = '/pacientes';
      const settings = {
        method: 'GET'
      }

      fetch(url, settings)
      .then(response => response.json())
      .then(data => {
         for(paciente of data){

            var table = document.getElementById("pacienteTable");
            var pacienteRow = table.insertRow();
            let tr_id = "tr_" + paciente.id;
            pacienteRow.id = tr_id;

            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                                      ' type="button" onclick="deletePacienteBy('+paciente.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                                      ' type="button" onclick="findPacienteBy('+paciente.id+')" class="btn btn-info btn_id">' +
                                      paciente.id +
                                      '</button>';

            pacienteRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_dni\">' + paciente.cedula + '</td>' +
                    '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
                     '<td class="td_fecha_ingreso">' + paciente.fechaIngreso + '</td>' +
                     '<td class="td_fecha_ingreso">' + paciente.domicilio.calle + '</td>' +
                     '<td class="td_fecha_ingreso">' + paciente.domicilio.numero + '</td>' +
                     '<td class="td_fecha_ingreso">' + paciente.domicilio.localidad + '</td>' +
                     '<td class="td_calle">' + paciente.domicilio.provincia + '</td>' +
                    '<td class="td_calle">' + paciente.email + '</td>' +
                    '<td>' + deleteButton + '</td>';
        };
      })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/get_pacientes.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })
})
