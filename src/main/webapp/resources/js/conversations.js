/**
 * Created by romab on 11/1/16.
 */

$( document ).ready(function() {

    $(window).resize(function(){
        $h = $(window).outerHeight()
        $('.chat').css({
            height: $h - $('.navbar').outerHeight() - $('footer').outerHeight() - $('.decor-default h6').outerHeight() - $('.answer-add').outerHeight() - 60
        });
        $('.friends-list').css({
            height: $('.chat').outerHeight() + $('.decor-default h6').outerHeight() + 80
        });
    });
    $(window).resize();
    
    

    $(".chat-users").on("click", ".user", function(){

        if ( !$(this).hasClass("activeUser") ){
            $(".chat-users .user").removeClass("activeUser");
            $(this).addClass("activeUser");
            cleanMessageList();
            refreshMessagesList();

            $("#chatTitle").text( $(this).find("#firstName").text() +" "
                +$(this).find("#lastName").text());

        }

        
    });


    $("#inputMessageField").bind("enterKey", function () {
        send();
    });

    $('#inputMessageField').keyup(function(e){
        if(e.keyCode == 13)
        {
            $(this).trigger("enterKey");
        }
    });

    $("#sentIcon").on('click',send())

    
});


function send() {

    var messageField = $('#inputMessageField');
    if (messageField.val() !== ""){
        var chatId = $(".activeUser").attr("id");

        var message = {
            chatId: chatId,
            messageText: messageField.val()
        };

        $.ajax({
            url:'/sendEvent/message',
            data: message,
            dataType:"json",
            success:function(res){
                console.log("message was sent");
            }

        });
        messageField.val("");
    }
    
}

function cleanMessageList() {
    $(".chat-body").empty()
}

function refreshMessagesList() {

    var currentChatId = $(".activeUser").attr('id');
    var url = "/messenger/chatMessages/";

    $.ajax({
        url : "/messenger/chatMessages/"+currentChatId,
        type : "GET",
        dataType : "json",
        success : function(data) {
            response(data);
        }
    });
}

function response(data) {


     var activeUserName = $(".activeUser").find("#firstName").text();

    $.each(data, function(index, element) {

        var id=element.authorId;
        var name = element.authorFirstName;
        var text = element.text;

        var large = '<div class="answer left '+ (activeUserName === name ? "bkg":"")+'"> ' +
            '<div class="avatar"> ' +
            '<img src="/image/avatar/'+id+'" alt="User name"> ' +
            '</div> ' +
            '<div class="name">'+name+'</div> ' +
            '<div class="text">'+text+'</div> ' ;

        $('.chat-body').append(large)

    });

    var objDiv = document.getElementById("chat");
    objDiv.scrollTop = objDiv.scrollHeight;
}





