function deletePacienteBy(id) {
    const url = `/pacientes/${id}`;
    const settings = {
        method: 'DELETE'
    };

    fetch(url, settings)
    .then(response => {
        if (response.ok) {
            // Remove the row from the table
            let row_id = "#tr_" + id;
            document.querySelector(row_id).remove();
            alert("Paciente eliminado exitosamente.");
        } else {
            alert("Error al eliminar el paciente.");
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert("Error al eliminar el paciente.");
    });
}
