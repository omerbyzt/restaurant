import {getCategoryList, useEffect, useState} from "react";
import axios from 'axios';
function Category(){

    const [categoryList, setCategoryList] = useState();

    useEffect(() => {
        fetch('http://localhost:8080/product/listcategory')
                .then(response => response.json())
                .then(data => {
                    setCategoryList(data);
                    console.log(categoryList)
                }).catch(e => {
                console.warn("e : ", e);
            });
        }, []
    );
    if (!categoryList) {
        return null;
    }

    const onClickCategoryButton = (a) => {


        window.location.reload();
    }

    return(
        <div>
            {
                categoryList.map(v => {
                    return (
                        <button className="btn btn-info" onClick={onClickCategoryButton.bind(this,v)}>{v}</button>
                    )
                })
            }
        </div>
    );
}

export default Category;