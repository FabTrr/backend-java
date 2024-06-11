function deleteBy(id) {
    const url = `/turnos/${id}`;
    const settings = {
        method: 'DELETE'
    };

    fetch(url, settings)
    .then(response => {
        if (response.ok) {
            // Remove the row from the table
            let row_id = "#tr_" + id;
            document.querySelector(row_id).remove();
            alert("Turno eliminado exitosamente.");
        } else {
            alert("Error al eliminar el turno.");
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert("Error al eliminar el turno.");
    });
}
