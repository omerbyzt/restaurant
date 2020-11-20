import './App.css';
import AddProduct from "./component/AddProduct";
import React, {useState, useEffect} from 'react';

import Table from 'react-bootstrap/Table'
import UpdateProduct from "./component/UpdateProduct";
import OrderList from "./component/OrderList";
import AddUser from "./component/AddUser";
import UserList from "./component/UserList";
import Product from "./component/Product";

function App() {

    const [content, setContent] = useState();
    const [isShowCard, setIsShowCard] = useState();
    const[isUpdateCard,setIsUpdateCard] = useState();
    const[obj,setObj] = useState();
    const[addUserPanel,setAddUserPanel]=useState();
    const[productTable,setProductTable] = useState("true");
    const[orderTable,setOrderTable]=useState();
    const[userTable,setUserTable] = useState();

    useEffect(() => {
            fetch('http://localhost:8080/product/listall')
                .then(response => response.json())
                .then(data => {
                    setContent(data);
                }).catch(e => {
                console.warn("e : ", e);
                setIsUpdateCard(true);
            });
        }, []
    )
    ;
    if (!content) {
        return null;
    }

    const clickedAddButton = () => {
        setIsShowCard(!isShowCard)
        console.log(isShowCard)
    }

    const onClickDeleteBtn = (e) =>{

        window.location.reload();
        return fetch('http://localhost:8080/product/delete/' + e.productID, {
            method: 'DELETE',
        }).then(response => response.json())
    }

    const onClickUpdateBtn = (e) => {
        setObj(e);
        setIsUpdateCard(!isUpdateCard)
    }

    const clickProductList = () => {
        setProductTable(!productTable)
    }

    const clickAddNewUser = () => {
        setAddUserPanel(!addUserPanel)
    }

    const clickUserList = () => {
        setUserTable(!userTable)
    }

    const clickOrderList = () => {
        setOrderTable(!orderTable)
    }

    return (
        <div>
            <button className="btn btn-info btn-lg mb-2 mt-2 ml-2" onClick={clickedAddButton}>Add New Product</button>
            <button className="btn btn-info btn-lg mb-2 mt-2 ml-2" onClick={clickProductList}>Product List</button>
            <button className="btn btn-info btn-lg mb-2 mt-2 ml-2" onClick={clickAddNewUser}>Add New User</button>
            <button className="btn btn-info btn-lg mb-2 mt-2 ml-2" onClick={clickUserList}>User List</button>
            <button className="btn btn-info btn-lg mb-2 mt-2 ml-2" onClick={clickOrderList}>Order List</button>
            {
                //Product Ekleme Ekranı
                isShowCard ? <AddProduct>
                </AddProduct>:null
            }
            {
                //Product Güncelleme
                isUpdateCard ?
                    <UpdateProduct
                        id={obj.productID}
                        name = {obj.productName}
                        desc = {obj.productDesc}
                        category = {obj.productCategory}
                        price = {obj.productPrice}
                    />:null
            }

            {
                productTable ?
                <Table striped bordered hover >
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
                            return (<tr align = "center">
                                <td>{v.productID}</td>
                                <td>{v.productName}</td>
                                <td>{v.productDesc}</td>
                                <td>{v.productCategory}</td>
                                <td>{v.productPrice}</td>
                                <tr>
                                    <button className = "btn btn-warning mr-2" onClick={onClickUpdateBtn.bind(this,v)}>Update</button>
                                    <button className = "btn btn-danger" onClick={onClickDeleteBtn.bind(this,v)}>Delete</button>
                                </tr>
                            </tr>)
                        })
                    }
                    </tbody>
                </Table>:null
            }

            {
                addUserPanel ?<AddUser></AddUser>:null
            }

            {
                orderTable ? <OrderList></OrderList>:null
            }

            {
                userTable ? <UserList></UserList> : null
            }

        </div>
    );
}

export default App;
