/*
 * Because Google's closure compiler optimizes away method names,
 * when interfacing with external libraries it is necessary to
 * protect those names from being optimized. Otherwise runtime 
 * errors will occur.
 * 
 * In order to do that, invocations of the form of: 
 * 
 *   Obj.method(args)
 * 
 * need to be changed to:
 * 
 *   Obj["method"](args)
 * 
 * This also applies for getting properties:
 * 
 *   Obj["property"]
 * 
 * */
$(document)["ready"](function() {
  
  var mainStructure = '\
   <table>\
    <tr>\
      <td valign="top">\
        <div class="game">\
          <div class="toolbar">\
            <select id="sizeSelector">\
              <option value="3,2">3x2</option>\
              <option value="3,3">3x3</option>\
              <option value="4,3">4x3</option>\
              <option value="6,4">6x4</option>\
            </select>\
            <button id="shuffleButton">Shuffle!</button>\
            <button id="resetButton">Reset</button>\
            <span>Moves: </span><span id="movesCount">0</button>\
          </div>\
          <div id="puzzle"></div>\
        </div>\
        <div style="margin-top:10px">Done with <a href="http://www.scala-js.org/">Scala.js</a></div>\
      </td>\
      <td valign="bottom">\
        <div id="message">\
        </div>\
      </td>\
    </tr>\
  </table>\
  <div id="hiddenContainer" class="hidden">\
    <a id="imgAnchor" href="./resources/2322324186_ca41fba641_o.jpg"></a>\
  </div>';
  
  $("body")["append"](mainStructure);
    
  window["jsUIController"] = makeJsUIController();
  var jsUIController = window["jsUIController"];

  var imgSrc = $("#imgAnchor")["attr"]("href");
  var tmpImg = new Image();
  tmpImg["src"] = imgSrc;
  tmpImg["onload"] = function() {
    $("#hiddenContainer")["append"](tmpImg);
    $(tmpImg)["attr"]("id", "srcImg");

    jsUIController["imageLoaded"]();

    ScalaJS.modules.com_sebnozzi_slidingpuzzle_SlidingPuzzleMain().main();
  };

});
