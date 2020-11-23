import AddProduct from "./AddProduct";
import React, {useState, useEffect} from 'react';

import Table from 'react-bootstrap/Table'
import UpdateProduct from "./UpdateProduct";
import {Link} from 'react-router-dom'
import Header from "./Header";
import axios from "axios";

function App() {

    const [content, setContent] = useState();
    const [isShowCard, setIsShowCard] = useState();
    const [isUpdateCard, setIsUpdateCard] = useState();
    const [obj, setObj] = useState();
    const [addUserPanel, setAddUserPanel] = useState();
    const [productTable, setProductTable] = useState("true");
    const [orderTable, setOrderTable] = useState();
    const [userTable, setUserTable] = useState();

    useEffect(() => {

            let uri = "http://localhost:8080/product/listall";

            fetch(uri, {
                method: 'get',
                headers: new Headers({
                    'Authorization': sessionStorage.getItem('token'),
                }),
            })
                .then(response => response.json())
                .then(data => {
                    setContent(data)
                    console.log()
                })
        }, []
    )
    ;
    if (!content) {
        return null;
    }

    const clickedAddButton = () => {
        setIsShowCard(!isShowCard)
    }

    const onClickDeleteBtn = (e) => {

        window.location.reload();
        console.log('http://localhost:8080/product/delete/' + e.productID)
        axios.delete('http://localhost:8080/product/delete/' + e.productID,
            {headers: {Authorization: sessionStorage.getItem('token')}});
    }

    const onClickUpdateBtn = (e) => {
        setObj(e);
        setIsUpdateCard(!isUpdateCard)
    }

    const clickProductList = () => {
        setProductTable(!productTable)
    }
    return (
        <div>
            <Header></Header>
            <Link to="/addproduct">
                <button className="btn btn-success productListAddProduct">+ Add Product</button>
            </Link>

            {
                //Product Ekleme Ekranı
                isShowCard ? <AddProduct>
                </AddProduct> : null
            }
            {
                //Product Güncelleme
                isUpdateCard ?
                    <UpdateProduct
                        id={obj.productID}
                        name={obj.productName}
                        desc={obj.productDesc}
                        category={obj.productCategory}
                        price={obj.productPrice}
                    /> : null
            }

            {
                productTable ?
                    <Table striped bordered hover className="productTable">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Product Name</th>
                            <th>Product Description</th>
                            <th>Product Category</th>
                            <th>Product Price</th>
                            <th>Buttons</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            content.map(v => {
                                return (<tr align="center">
                                    <td>{v.productID}</td>
                                    <td>{v.productName}</td>
                                    <td>{v.productDesc}</td>
                                    <td>{v.productCategory}</td>
                                    <td>{v.productPrice}</td>
                                    <tr>
                                        <button className="btn btn-warning mr-2"
                                                onClick={onClickUpdateBtn.bind(this, v)}>Update
                                        </button>
                                        <button className="btn btn-danger"
                                                onClick={onClickDeleteBtn.bind(this, v)}>Delete
                                        </button>
                                    </tr>
                                </tr>)
                            })
                        }
                        </tbody>
                    </Table> : null
            }

        </div>
    );
}

export default App;
