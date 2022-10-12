const fileAddButton = document.querySelector(".add-button");  //추가 버튼
const fileInput = document.querySelector(".file-input");      //input창
const submitButton = document.querySelector(".submit-button")  //등록하기 버튼

let productImageFiles = new Array();  //이미지를 담을 리스트 생성

fileAddButton.onclick = () => {   //추가 버튼을 누르면
    fileInput.click();            //input창 실행
};

fileInput.onchange = () => {
    const formData = new FormData(document.querySelector("form"));
    let changeFlge = false;

    formData.forEach((value) => {
        if (value.size != 0) {
            productImageFiles.push(value);
            console.log(productImageFiles);
            changeFlge = true;
        }
    });
    if (changeFlge) {
        getImagePreview();
        fileInput.value = null;
    }
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


submitButton.onclick = () => {  //등록 하기 버튼을 클릭하면
    const productInputs = document.querySelectorAll(".product-input");  //상품등록 input부분

    let formData = new FormData();  //폼데이터 객체 생성

    //폼데이터 객체에 정보 저장
    formData.append("category", productInputs[0].value);
    formData.append("name", productInputs[1].value);
    formData.append("price", productInputs[2].value);
    formData.append("color", productInputs[3].value);
    formData.append("size", productInputs[4].value);
    formData.append("infoSimple", productInputs[5].value);
    formData.append("infoDetail", productInputs[6].value);
    formData.append("infoOption", productInputs[7].value);
    formData.append("infoManagement", productInputs[8].value);
    formData.append("infoShipping", productInputs[9].value);

    productImageFiles.forEach((file) => {
        formData.append("files", file);
    })

    formData.forEach((value, key) => {
        console.log(key);
        console.log(value);
        console.log();
    })

    requset(formData);
}

function requset(formData) {
    $.ajax({
        async: false,
        type: "post",
        url: "/api/admin/product",
        enctype: "multipart/form-data",
        contentType: false,
        processData: false,
        data: formData,
        dataType: "json",
        success: (response) => {
            alert("상품 등록 완료");
        },
        error: (error) => {
            alert("상품 등록 실패");
            console.log(error);
        }

    });
}