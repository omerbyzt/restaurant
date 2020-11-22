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
        price: ""
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    addProduct = (e) => {
        const {name, desc, category, price} = this.state;

        const newProduct = {
            productName: name,
            productDesc: desc,
            productCategory: category,
            productPrice: price
        }
        axios.post("http://localhost:8080/product/add", newProduct,
            {headers:{Authorization: sessionStorage.getItem('token')}}
            );
    }

    render() {
        const {name, desc, category, price, isShowCard} = this.state;
        return (
            <div>
            <Header></Header>
                <div className="col-md-12 mb-4">
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
                                            <label htmlFor="category">Product Category</label>
                                            <input type="text"
                                                   className="form-control"
                                                   placeholder="Enter Product Category"
                                                   name="category"
                                                   id="categoryInput"
                                                   value={category}
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
                                        <button className="btn btn-danger btn-block" onClick={this.addProduct}>Add Product</button>
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