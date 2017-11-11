window.onload = function(){
    $("#postcode-lookup").click(function(){
        var postcodeApi = "https://api.getAddress.io/find/";
        var postcode = $("input#postcode").val();
        var apiKey = "eV4ppm_xFkCVVPM8VC9zXw11035";
        
        var ajaxUrl = postcodeApi + postcode + "?api-key=" + apiKey;
        
        $.ajax({
            url: ajaxUrl,
            dataType: "json",
            success: function(result){
                // Populate address picker
                $("#available-addresses").empty();
                
               
                for(i = 0; i < result.addresses.length; i++){
                    $("#available-addresses").append("<option>" + result.addresses[i].replace(new RegExp(" ,", "g"), "") + "</option>")
                }
                
                // Show the address picker
                $("#address-lookup").show();
            },
            error: function(result){
                //Hide the address picker
                $("#address-lookup").hide();
                
                // Set the textbox to be empty
                $("input#postcode").val("");
                
                // Display an invalid postcode message
                alert("Invalid postcode");
            }
        })
    });
};
