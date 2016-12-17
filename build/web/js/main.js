function openRightMenu() {
    $('#menuButton').hide();
    var x = document.getElementById("sideNav");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
    document.getElementById("rightMenu").style.display = "block";
}
function closeRightMenu() {
    $('#menuButton').show();
    var x = document.getElementById("sideNav");
    x.className = x.className.replace(" w3-show", "");
    document.getElementById("rightMenu").style.display = "none";
}

$(document).ready(function () {

    menuTitle = $("#menuTitle").html();
    $("#navTitle").html("<b>" + menuTitle + "</b>");
    $('#myNoteContents').summernote({
        height: 300, // set editor height
        minHeight: null, // set minimum height of editor
        maxHeight: null, // set maximum height of editor
        focus: false, // set focus to editable area after initializing summernote

        toolbar: [
            // [groupName, [list of button]]
            ['style', ['bold', 'italic', 'underline', 'clear']],
            ['font', ['strikethrough', 'superscript', 'subscript']],
            ['fontsize', ['fontsize']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['insert', ['link', 'picture', 'hr']],
            ['misc', ['codeview']]
        ],
        popover: {
            image: [
                ['imagesize', ['imageSize100', 'imageSize50', 'imageSize25']],
                ['float', ['floatLeft', 'floatRight', 'floatNone']],
                ['remove', ['removeMedia']]
            ]
        },
        callbacks: {
//            onImageUpload: function (files, editor, welEditable) {
//                sendFile(files[0], editor, welEditable);
//            },
//
//            onMediaDelete: function ($target, editor, $editable) {
//                console.log($target[0].src); // img 
//
//                // remove element in editor 
//                $target.remove();
//
//                alert("delete!");
//            }
        }
    }
    );

    function sendFile(file, editor, welEditable) {
        data = new FormData();
        data.append("uploadFile", file);
        $.ajax({
            data: data,
            type: "POST",
            url: "noteImageUpload",
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                editor.insertImage(welEditable, data.url);
            }
        });
    }


    $('#collaboNoteContents').summernote({
        height: 300, // set editor height
        minHeight: null, // set minimum height of editor
        maxHeight: null, // set maximum height of editor
        focus: false, // set focus to editable area after initializing summernote
        toolbar: [
            // [groupName, [list of button]]
            ['style', ['bold', 'italic', 'underline', 'clear']],
            ['font', ['strikethrough', 'superscript', 'subscript']],
            ['fontsize', ['fontsize']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['insert', ['link', 'picture', 'hr']],
            ['misc', ['codeview']]
        ],
        popover: {
            image: [
                ['imagesize', ['imageSize100', 'imageSize50', 'imageSize25']],
                ['float', ['floatLeft', 'floatRight', 'floatNone']],
                ['remove', ['removeMedia']]
            ]
        },

        callbacks: {
//            onImageUpload: function (files) {
////                $('#myNoteContents').summernote('insertNode', imgNode);
//            },
//
//            onMediaDelete: function ($target, editor, $editable) {
//                console.log($target[0].src); // img 
//
//                // remove element in editor 
//                $target.remove();
//
//                alert("delete!");
//            }
        }


    });
}
);
