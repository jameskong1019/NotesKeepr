$(document).ready(function () {

    $("#saveButton").click(function () {

        note = {
            action: $("#action").val(),
            page: $("#page").val(),
            noteID: $("#noteID").val(),
            title: $("#collaboNoteTitle").val(),
            contents: $("#collaboNoteContents").summernote('code')
        };

        if (note.title.length != 0) {
            $.post("collaboratedNotes", note, function (response) {
                if (response === "true") {
                    $(location).attr('href', 'collaboratedNotes');
                } else {
                    alert("Error: Please try again!");
                }
            });
        } else
        {
            alert("Please enter title for note!");
        }
    });

});




