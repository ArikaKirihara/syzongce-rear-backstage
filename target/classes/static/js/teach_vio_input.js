/**违规违纪手填 */
let previewContainer = document.querySelector('.result-preview');
let previewBox = previewContainer.querySelectorAll('.preview');
document.querySelectorAll('.form_btn .f_btn').forEach(f_btn =>{
    f_btn.onclick = () =>{
        previewContainer.style.display = 'flex';
        let name = f_btn.getAttribute('data-name');
        
        previewBox.forEach(preview =>{
            let target = preview.getAttribute('data-target');
            if(name == target ){
                preview.classList.add('active');
            }
        });
    };
});

previewBox.forEach(r_operate =>{
    r_operate.onclick = () =>{
        r_operate.classList.remove('active');
        previewContainer.style.display = 'none';
    };
});

