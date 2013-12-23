$(document).ready(function() {
    
  window.jsUIController = makeJsUIController();
  var jsUIController = window.jsUIController;
  
  $("#sizeSelector").on("change", function() {
    jsUIController.sizeChanged();
  });

  $("#shuffleButton").on("click", function() {
    jsUIController.shuffleClicked();
  });

  $("#resetButton").on("click", function() {
    jsUIController.resetClicked();
  });

  jsUIController.imageLoaded();

  jsUIController.setupGrid(3,2);
    
  ScalaJS.modules.com_sebnozzi_slidingpuzzle_SlidingPuzzleMain().main();
  
});

