
function makeJsUIController() {

  var tiles = [];
  
  var imgWidth = undefined;
  var imgHeight = undefined;
  var tileWidth = undefined;
  var tileHeight = undefined;
  
  var currentSize = undefined;
  
  return {
    setupGrid: function(cols, rows) {
      
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
    tileClicked: function(id) {
      console.log("clicked on: ", id);
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
      var tile = findTile(tileId);
      console.log("Showing tile: ", tile);
      if(animate) {
        $(tile).show(400);
      } else {
        $(tile).show();
      }
    },
    makeTileHidden: function(tileId) {
      var tile = findTile(tileId);
      console.log("Hiding tile: ", tile);
      $(tile).hide();
    },
    moveTileTo: function(tileId, targetCol, targetRow, animate) {
      var tile = findTile(tileId);
      console.log("Moving tile: ", tile, " to: ", col, row);
      var top = tileHeight * targetRow;
      var left = tileWidth * targetCol;
      if(animate) {
        $(tile).animate({
          left: "" + top,
          top: "" + left,
        }, 200, "swing");
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
    onSizeChanged: function() {
      var size = this.getSelectedSize();
      console.log("size changed:", size.cols, size.rows);
      this.setupGrid(size.cols, size.rows);
    },    
    onShufflePressed: function() {
      var size = this.getSelectedSize();
      console.log("current size:", size.cols, size.rows);
      console.log("shuffle");
    },    
    onResetPressed: function() {
      console.log("reset");
    },    
    onImageLoaded: function() {
      var srcImg = $("#srcImg")[0];
      imgWidth = srcImg.width;
      imgHeight = srcImg.height;
    }
  };
    
}

$(document).ready(function() {

});
  


  

