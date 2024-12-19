//Login alert: welcome
const button = document.getElementById('login');
button.onclick = function () {
    const username = document.getElementById('username').value;
    alert('Bienvenid@' + username);
};

//Cart count 
let likeCount = 0;
function incrementCartCount() {
    likeCount++;
    document.getElementById('cartCount').textContent = likeCount;
}
document.getElementById('btnOrange1').addEventListener('click', incrementCartCount);
document.getElementById('btnOrange2').addEventListener('click', incrementCartCount);

//Dynamic image change
const image = document.getElementById('hoverImage');

// Change the pricipal image
image.addEventListener('mouseenter', () => {
    image.src = './img/comida-mexicana.jpg';
});

// Back to the principal image
image.addEventListener('mouseleave', () => {
    image.src = './img/comida-mexicana2.jpg';
});


