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

  window["jsUIController"] = makeJsUIController();
  var jsUIController = window["jsUIController"];  

  ScalaJS.modules.com_sebnozzi_slidingpuzzle_SlidingPuzzleMain().main();

});
