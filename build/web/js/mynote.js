$(document).ready(function () {

    $("#composeButton").click(function () {
        
        var note = {
            action: $("#action").val(),
            page: $("#page").val(),
            title: $("#myNoteTitle").val(),
            contents: $("#myNoteContents").summernote('code')
        };

        if (note.title.length != 0) {

            $.post("notes", note, function (response) {
                if (response === "true") {
                    $(location).attr('href', 'notes');
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




