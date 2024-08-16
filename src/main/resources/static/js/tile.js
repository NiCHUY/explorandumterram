const element = document.querySelector('.element');

element.addEventListener('mouseover', function() {
    element.style.opacity = '0.7';
    element.style.backgroundColor = 'lightgreen';
});

element.addEventListener('mouseout', function() {
    element.style.opacity = '1';
    element.style.backgroundColor = 'lightblue';
});