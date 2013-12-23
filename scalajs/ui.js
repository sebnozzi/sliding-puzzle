
function makeJsUIController() {

  var tiles = [];
  
  var imgWidth = undefined;
  var imgHeight = undefined;
  var tileWidth = undefined;
  var tileHeight = undefined;
  
  var currentSize = undefined;
  
  var resetCallback = function(){};
  var shuffleCallback = function(){};
  var tileCallback = function(tileId){};
  var sizeCallback = function(newCols, newRows){};
  
  return {
    imageLoaded: function() {
      var srcImg = $("#srcImg")[0];
      imgWidth = srcImg.width;
      imgHeight = srcImg.height;
    },
    setupGrid: function(cols, rows) {
      
      console.log("Setting up grid: ", cols, "x", rows);
      
      $("#puzzle").empty();
      tiles = [];
      currentSize = {"cols": cols, "rows": rows};
    
      tileWidth = Math.floor(imgWidth / cols);
      tileHeight = Math.floor(imgHeight / rows);
      
      for(row = 0; row < rows; row++) {
        for(col = 0; col < cols; col++) {
          var id = "canvas_"+col+"_"+row;
          var tile = $('<canvas id="'+id+'" data-col="'+col+'" data-row="'+row+
            '" class="absolute" width="'+tileWidth+'" height="'+tileHeight+'"/>');
    
          $("#puzzle").append(tile);
          tile = tile.get(0);
    
          var img = $("#srcImg").get(0);
          var ctx = tile.getContext("2d");
          var sx = col * tileWidth;
          var sy = row * tileHeight;
          ctx.drawImage(img, sx, sy, tileWidth, tileHeight, 0, 0, tileWidth, tileHeight);
          ctx.strokeRect(0, 0, tileWidth, tileHeight);
          
          $(tile).css("left", sx);
          $(tile).css("top", sy);
          
          tiles.push(tile);
        }
      }
    
      var _thisClosure = this;
      $(tiles).click(function() {
        var col = parseInt($(this).attr("data-col"));
        var row = parseInt($(this).attr("data-row"));
        var tileId = $(this).attr("id");
        _thisClosure.tileClicked(tileId);
      });
    
    },
    shuffleClicked: function() {
      shuffleCallback();
    },
    resetClicked: function() {
      resetCallback();
    },
    sizeChanged: function() {
      var size = this.getSelectedSize();
      sizeCallback(size.cols, size.rows);
    },
    tileClicked: function(tileId) {
      //console.log(tileCallback);
      tileCallback(tileId);
    },    
    findTile: function(id) {
      for(i = 0; i < tiles.length; i++) {
        if($(tiles[i]).attr("id") == id)
          return tiles[i];
      }
      return undefined;
    },    
    setMovesCount: function(newValue) {
      $("#movesCount").html(newValue);
    },
    getTileIds: function() {
      var ids = [];
      for(i=0; i<tiles.length; i++) {
        ids.push($(tiles[i]).attr("id"));
      }
      return ids;
    },
    makeTileVisible: function(tileId, animate) {
      var tile = this.findTile(tileId);
      if(animate) {
        $(tile).show(400);
      } else {
        $(tile).show();
      }
    },
    makeTileHidden: function(tileId) {
      var tile = this.findTile(tileId);
      $(tile).hide();
    },
    moveTileTo: function(tileId, targetCol, targetRow, animate) {
      var tile = this.findTile(tileId);
      //console.log("Moving tile: ", tile, " to: ", col, row);
      var left = tileWidth * (targetCol);
      var top = tileHeight * (targetRow);
      if(animate) {
        $(tile).animate({
          left: "" + left,
          top: "" + top,
        }, 100);
      } else {
        $(tile).css("top", top); 
        $(tile).css("left", left); 
      }
    },
    getSelectedSize: function() {
      var commaSeparatedNumbers = $("#sizeSelector").val();
      var parts = commaSeparatedNumbers.split(",");
      var cols = parseInt(parts[0]);
      var rows = parseInt(parts[1]);
      return {"cols": cols, "rows": rows};
    },
    setSelectedSize: function(cols, rows) {
      var targetVal = cols + "," + rows;
      console.log("Selecting size value: " + targetVal);
      $("#sizeSelector").val(targetVal);
    },
    onTileClicked: function(callback) {
      tileCallback = callback;
    },    
    onSizeChanged: function(callback) {
      sizeCallback = callback;
    },    
    onShufflePressed: function(callback) {
      shuffleCallback = callback;
    },    
    onResetPressed: function(callback) {
      resetCallback = callback;
    }
  };
    
}

  


  

