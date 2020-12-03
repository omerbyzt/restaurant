import React, {Component} from 'react';
import axios from 'axios';
import Header from "./Header";
import {Link} from 'react-router-dom'

class AddProduct extends Component {
    state = {
        isShowCard: true,
        id: "",
        name: "",
        desc: "",
        category: "",
        price: "",
        categoryList:[],
        btnCategoryName:"Select Category Name",
        selectedCategoryId : ""
    }

    componentDidMount() {
        const {categoryList} = this.state

        let uri = "http://localhost:8080/category/list-category";

        fetch(uri, {
            method: 'get',
            headers: new Headers({
                'Authorization': sessionStorage.getItem('token'),
            }),
        })
            .then(response => response.json())
            .then(data => {
                this.setState({
                    categoryList: data
                })
            })
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    addProduct = (e) => {
        const {name, desc, price,selectedCategoryId,btnCategoryName} = this.state;

        const newProduct = {
            productName: name,
            productDesc: desc,
            productCategory: btnCategoryName,
            productPrice: price,
        }
        axios.post("http://localhost:8080/category/add-product/"+selectedCategoryId, newProduct,
            {headers:{Authorization: sessionStorage.getItem('token')}}
            );
    }

    onClickItem = (e) => {
        this.setState({
            btnCategoryName: e.name,
            selectedCategoryId:e.id
        })
    }

    render() {
        const {name, desc, category, price, isShowCard,categoryList,btnCategoryName} = this.state;
        return (
            <div>
            <Header></Header>

                <div className="col-md-6 mx-auto mb-4 mt-4">
                    {
                        isShowCard ?
                            <div className="card" align="left">
                                <div className="card-header" align="center">
                                    <h4>Add Product</h4>
                                </div>
                                <div className="card-body">
                                    <form>
                                        <div className="form-group">
                                            <label htmlFor="name">Product Name</label>
                                            <input type="text"
                                                   className="form-control"
                                                   placeholder="Enter Product Name"
                                                   name="name"
                                                   id="nameInput"
                                                   value={name}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="desc">Product Description</label>
                                            <input type="text"
                                                   className="form-control"
                                                   placeholder="Enter Product Description"
                                                   name="desc"
                                                   id="descInput"
                                                   value={desc}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="price">Product Price</label>
                                            <input type="number"
                                                   className="form-control"
                                                   placeholder="Enter Product Price"
                                                   name="price"
                                                   id="priceInput"
                                                   value={price}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="dropdown">
                                            <label htmlFor="price">Product Category : </label>
                                            <button className="btn btn-info dropdown-toggle dropdownCss" type="button"
                                                    id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                                    aria-expanded="true">
                                                {btnCategoryName}
                                            </button>
                                            <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                {
                                                    categoryList.map(v=>{
                                                        return(
                                                            <a className="dropdown-item" onClick={this.onClickItem.bind(this,v)}>{v.name}</a>
                                                        )
                                                    })
                                                }
                                            </div>
                                        </div>

                                        <button className="btn btn-warning btn-block addProductButtonCss" onClick={this.addProduct}>Add Product</button>
                                    </form>
                                </div>
                            </div> : null
                    }
                </div>
            </div>


        );
    }

}

export default AddProduct;