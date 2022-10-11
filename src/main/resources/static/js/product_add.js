const fileAddButton = document.querySelector(".add-button");  //추가 버튼
const fileInput = document.querySelector(".file-input");      //input창
const submitButton = document.querySelector(".submit-button")  //등록하기 버튼

let productImageFiles = new Array();  //이미지를 담을 리스트 생성

fileAddButton.onclick = () => {   //추가 버튼을 누르면
    fileInput.click();            //input창 실행
};

fileInput.onchange = () => {


    const formData = new FormData(document.querySelector("form"));

    formData.forEach((value) => {
        if (value.size != 0) {
            productImageFiles.push(value);
            console.log(productImageFiles);
            getImagePreview();
            fileInput.value = null;
        }
    })
}

function getImagePreview() {  //이미지 미리보기 함수 생성
    const prouctImages = document.querySelector(".product-images");  //미리보기 area

    prouctImages.innerHTML = "";  //innerHTML 준비?





    productImageFiles.forEach((file, i) => {  //이미지 담긴 파일리스트에 forEach문

        const reader = new FileReader();  //FileReader 객체 생성

        reader.onload = (e) => {
            prouctImages.innerHTML += `
                <div class="img-box">
                    <i class="fa-solid fa-xmark"></i>
                    <img class="product-img" src="${e.target.result}">
                </div>
            `;

            const deleteButton = document.querySelectorAll(".fa-xmark");
            deleteButton.forEach((xbutton, index) => {
                xbutton.onclick = () => {
                    if (confirm("상품 이미지를 지우시겠습니까?")) {
                        productImageFiles.splice(index, 1);
                        getImagePreview();
                    }
                }
            })
        }


        setTimeout(() => { reader.readAsDataURL(file); }, (i * 100));
    });
}