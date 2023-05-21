let previewContainer = document.querySelector('.result-preview');
let previewBox1 = previewContainer.querySelectorAll('.preview1');
let r_btn = previewContainer.querySelectorAll('.r_operate');

document.querySelectorAll('.content .sheet .insert').forEach(insert => {
    insert.onclick = () => {
        previewContainer.style.display = 'flex';
        let name = insert.getAttribute('data-name');
        previewBox1.forEach(preview1 => {
            let target = preview1.getAttribute('data-target');
            if (name == target) {
                preview1.classList.add('active');
            }
        });
    };
});

r_btn.forEach(r_operate => {
    r_operate.onclick = () => {
        // r_operate.classList.remove('active');
        previewContainer.style.display = 'none';
    };
});
previewContainer.onclick = function(e){
    console.log(e);
    if(e.target==previewContainer){
        previewContainer.style.display = 'none'
    }
}