$(function () {
    $('#summernote').summernote({
        weight:500,
        height: 300,                 // set editor height
        toolbar: false,
        placeholder: 'write here...',
        dialogsInBody: true,
        toolbar: [
            // [groupName, [list of button]]
            ['style', ['bold', 'italic', 'underline', 'clear']],
            ['font', ['strikethrough', 'superscript', 'subscript']],
            ['fontsize', ['fontsize']],
            ['color', ['color']],
            ['table', ['table']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['picture', ['picture']],
            ['help', ['help']]
        ],
        minHeight: null,             // set minimum height of editor
        maxHeight: null,             // set maximum height of editor
        focus: true,
        callbacks: {
            onImageUpload: function (files, editor, $editable) {
                sendFile(files);
            }
        }
    });
    function sendFile(files, editor, $editable) {
        var data = new FormData();
        data.append("Mfile", files[0]);
        $.ajax({
            data: data,
            type: "POST",
            url: "/post/upload",
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                console.log(data)
                $('#summernote').summernote('insertImage', data);

            },
            error: function () {
                swal("上传失败","error");
            }
        });
    }
    $("#createPost").click(function() {

        var postTitle=$("#postTitle").val();
        if(postTitle){
            var markupStr = $('#summernote').summernote('code');

                $.ajax({
                    type:"POST",
                    url:"/post/create",
                    data:{
                       "postTitle":postTitle,
                        "markupStr":markupStr
                    },
                    success:function () {
                       swal("恭喜！","帖子发部成功","success")
                        window.location.href = "/post";
                    }
                })
        }else {
            swal("请填写标题")
        }

    });
})
