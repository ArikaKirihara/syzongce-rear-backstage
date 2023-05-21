let previewContainer = document.querySelector('.result-preview');
let previewBox = previewContainer.querySelectorAll('.preview');

document.querySelectorAll('.table_control .t_btn .r_btn').forEach(r_btn => {
    r_btn.onclick = () => {
        previewContainer.style.display = 'flex';
        let name = r_btn.getAttribute('data-name');
        previewBox.forEach(preview => {
            let target = preview.getAttribute('data-target');
            if (name == target) {
                preview.classList.add('active');
            }
        });
    };
});

previewBox.forEach(r_operate => {
    r_operate.onclick = () => {
        r_operate.classList.remove('active');
        previewContainer.style.display = 'none';
    };
});

