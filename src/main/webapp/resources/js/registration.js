/**
 * Created by romab on 10/3/16.
 */



$(document).ready(function () {



});


function validate() {

    var errors = [];

    var firstName = $("#firstName").val();
    var lastName = $("#lastName").val();
    var birthday = $("#birthday").val();
    var email = $("#email").val();
    var login = $("#login").val();
    var password = $("#password").val();
    var repeatPass  = $("#repeatPass").val();

    var regexEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    var regexNames = /[a-z]/i;
    var regexDate =  /^[0123][1-9]([/])\d{2}\1\d{4}$/;
    var regexPass =  /[a-z0-9]{6,16}/i;

    if(!regexNames.test(firstName) || firstName ==""){
        errors.push("invalid first name");
    }

    if(!regexNames.test(lastName)){
        errors.push("invalid last name");
    }

    if(!regexNames.test(login)){
        errors.push("invalid login");
    }

    if( !regexEmail.test(email) ){
        errors.push("invalid email (example: example@mail.com)")
    }

    if (!regexDate.test(birthday)){
        errors.push("invalid date (example: 11/11/2011)")
    }

    if (!regexPass.test(password)){
        errors.push("invalid password (example: pa12345ssWORD) ")
    }

    if ( !(password === repeatPass) ){
        errors.push("passwords does not match")
    }

    if (errors.length >= 1){
        for (var i=0; i<errors.length; i++)
        $("#warningMessage").append('<p>'+errors[i]+'</p>');
        $("#warningMessage").css('display','block');

        return false;

    }else return true;


}


















