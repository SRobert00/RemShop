
// Get the modal
var addProductModal = document.getElementById('id02');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == addProductModal) {
        addProductModal.style.display = "none";
    }
}

document.getElementById("adminPanelButton").style.display="block";
document.getElementById("adminPanelButton").style.transition="width 1s, transform 1s";
document.getElementById("adminPanelButton").style.width="0px";
document.getElementById("adminPanelButton").style.transform="translateX(0px)";

document.getElementById("openAddProductModalButton").addEventListener("mouseover", mouseOver);
document.getElementById("openAddProductModalButton").addEventListener("mouseout", mouseOut);
document.getElementById("adminPanelButton").addEventListener("mouseover", mouseOver);
document.getElementById("adminPanelButton").addEventListener("mouseout", mouseOut);

function mouseOver(){
    document.getElementById("adminPanelButton").style.display="block";
    document.getElementById("adminPanelButton").style.transition="width 1s, transform 1s";
    document.getElementById("adminPanelButton").style.width="150px";
    document.getElementById("adminPanelButton").style.transform="translateX(6px)";
    document.getElementById("openAddProductModalButton").style.transform="rotate(135deg)";
}

function mouseOut(){
    document.getElementById("adminPanelButton").style.display="block";
    document.getElementById("adminPanelButton").style.transition="width 1s, transform 1s";
    document.getElementById("adminPanelButton").style.width="0px";
    document.getElementById("adminPanelButton").style.transform="translateX(0px)";
    document.getElementById("openAddProductModalButton").style.transform="rotate(0deg)";
}