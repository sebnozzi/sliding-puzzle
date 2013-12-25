
$(document)["ready"](function() {
    
  window["jsUIController"] = makeJsUIController();
  var jsUIController = window["jsUIController"];
  
  $("#sizeSelector")["on"]("change", function() {
    jsUIController["sizeChanged"]();
  });

  $("#shuffleButton")["on"]("click", function() {
    jsUIController["shuffleClicked"]();
  });

  $("#resetButton")["on"]("click", function() {
    jsUIController["resetClicked"]();
  });
  
  var imgSrc = $("#imgAnchor")["attr"]("href");
  var tmpImg = new Image() ;
  tmpImg["src"] = imgSrc;
  tmpImg["onload"] = function() {
    $("#hiddenContainer")["append"](tmpImg);
    $(tmpImg)["attr"]("id", "srcImg");
    
    jsUIController["imageLoaded"]();
    jsUIController["setupGrid"](3,2);
      
    ScalaJS.modules.com_sebnozzi_slidingpuzzle_SlidingPuzzleMain().main();
    jsUIController["shuffleClicked"]();
  } ;
  
});

