
$(document).ready(function() {
    $("#taable").DataTable({
        'aoColumnDefs': [{
            'bSortable': false,
            'aTargets': [-1] /* 1st one, start by the right */
        }]
    });


    $('selector').css('cursor', 'pointer');

    $("#Search").hover(function (){
        d3.selectAll("p").style("background-color","grey !important");

        $(this).animate({
            opacity: "100%",
            marginTop: "+=10px"
        })
        $(this).find(".fa-search").css("color","lightblue");
    },function (){
        $(this).animate({
            opacity: "80%",
            marginTop: "-=10px",
            color:"white!important"
        })
        $(this).find(".fa-search").css("color","white");
    });

    $(".nav-menu").hover(function (){
        $(this).animate({
            color:"white",
            fontSize: "18px",
            borderBottom: "1px solid white!important"
        },300)
    },function (){
        $(this).animate({
            color:"white",
            fontSize: "16px",
            borderBottom: "none"
        },300)
    })

    $("#login").on('mouseenter',function (e){
            $(this).find('span').css("background-color","green !important");
    }).on("mouseout",function (e){
        $(this).find('span').css("background-color","white !important");
    })

    $("#login").hover(function (){
        $(this).animate({
            marginTop:"+=10px"
        },300)
        // a = li.find("a")
        // console.log(a);
    },function (){
        $(this).animate({
            marginTop:"-=10px"
        },300)
    })
    $("#logout").hover(function (){
        $(this).animate({
            marginTop:"+=10px"
        },300)
        // a = li.find("a")
        // console.log(a);
    },function (){
        $(this).animate({
            marginTop:"-=10px"
        },300)
    })
    $("#show_forma").click(function (){
        $("#forma").show(500);
        console.log("Stisna!");
    })
})
