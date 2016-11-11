/**
 * Created by romab on 10/1/16.
 */

// autocompletion for search page
$(document).ready(function() {
    
    $(function () {
        $.ajax({
            url: "/autocomplete/userPage",
            dataType: "json",
            success: function (data) {
                fillAddList(data)
            }
        });
    });
});


function fillAddList(data) {
    
    var options = $("#discToAdd");
    $.each(data, function(key, val) {
        options.append('<option>'+ val +'</option>');
    });
    
}



    


