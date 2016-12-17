$(document).ready(function () {

    $("#addCollboratorButton").click(function () {
        $("#listCollaborator").hide();
        $("#addCollaborator").show();
        $("#addCollboratorButton").hide();
    });

    $("#cancelCollaboratorButton").click(function () {
        $("#listCollaborator").show();
        $("#addCollaborator").hide();
        $("#addCollboratorButton").show();
        $("#usernameToAdd").val("");
        $("#checkUser").html("");
    });

    $("#usernameToAdd").keyup(function () {
        var username = $("#usernameToAdd").val();

        if (username.length == 0)
        {
            $("#submitCollaboratorButton").prop('disabled', true);
            $("#checkUser").html("");
        }

        var user = {
            action: "checkUser",
            username: username,
            noteID: $("#noteID").val()
        }
        if (username.length != 0) {
            $.post("account", user, function (response) {
                if (response === "true") {
                    $("#submitCollaboratorButton").prop('disabled', false);
                    $("#checkUser").html("<span id='message'>Available username</span>");

                } else
                {
                    $("#submitCollaboratorButton").prop('disabled', true);
                    $("#checkUser").html("<span id='error'>Not available username</span>");
                }
            });
        }

    });

    $("#submitCollaboratorButton").click(function () {

        var username = $("#usernameToAdd").val();

        var collaborator = {
            action: "addCollaborator",
            username: username,
            noteID: $("#noteID").val()
        }

        $.post("notes", collaborator, function (response) {

            if (response === "true") {
                var collaboratorList = $("#listCollaborator").html();

                if (collaboratorList.indexOf("No collaborators") != -1) {

                    collaboratorList = username + ' <button id="removeCollaboratorButton" value="' + username + '"><i class="fa fa-minus-square fa-lg" aria-hidden="true"></i></button>';

                    $("#listCollaborator").html(collaboratorList);
                } else {
                    collaboratorList += ' | ' + username + ' <button id="removeCollaboratorButton" value="' + username + '"><i class="fa fa-minus-square fa-lg" aria-hidden="true"></i></button>';

                    $("#listCollaborator").html(collaboratorList);
                }
                $("#listCollaborator").show();
                $("#addCollaborator").hide();
                $("#addCollboratorButton").show();
                $("#usernameToAdd").val("");
                $("#checkUser").html("");

            } else
            {
                $("#listCollaborator").show();
                $("#addCollaborator").hide();
                $("#addCollboratorButton").show();
                $("#usernameToAdd").val("");
                $("#checkUser").html("");

                alert('Error: Adding collaborator is failed.');

            }

        });

    });

    $("#saveButton").click(function () {
        var note = {
            action: $("#action").val(),
            page: $("#page").val(),
            noteID: $("#noteID").val(),
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

$(document).on("click", "#removeCollaboratorButton", function () {
    var username = $(this).val();
    var result = confirm("Do you want to remove " + username + " from collaborator?");

    var note = {
        action: "removeCollaborator",
        noteID: $("#noteID").val(),
        username: username
    };

    if (result == true) {
        $.post("notes", note, function (response) {

            if (response !== "false") {
                var userobj = JSON.parse(response);
                var userlist = userobj.userlist;
                var collaboratorList = '';

                if (userlist.length != 0) {

                    for (var index = 0; index < userlist.length; index++)
                    {
                        collaboratorList += userlist[index].username + ' <button id="removeCollaboratorButton" value="' + userlist[index].username + '"><i class="fa fa-minus-square fa-lg" aria-hidden="true"></i></button>';
                        if (index != userlist.length - 1)
                        {
                            collaboratorList += ' | ';
                        }

                    }

                    $("#listCollaborator").html(collaboratorList);

                } else if (userlist.length == 0) {
                    $("#listCollaborator").html("No collaborators");
                }

                response_var = response;

            } else
            {
                alert("Error: Failed to remove collaborator");
            }
        });
    }
});




