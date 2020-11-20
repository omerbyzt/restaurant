import React, {Component} from 'react';
import axios from 'axios';
import PropTypes from 'prop-types';

class Product extends Component {

    state = {
        isVisible: false,
        isUpdate: false,
        productID1: this.props.productID,
        productName1: this.props.productName,
        productDesc1: this.props.productDesc,
        productCategory1: this.props.productCategory,
        productPrice1: this.props.productPrice
    }

    static defaultProps = {
        productName: "No Name Information",
        productDesc: "No Desc Information",
        productCategory: "No Name Information",
        productPrice: -1
    }

    headerClick = (e) => {
        this.setState({
            isVisible: !this.state.isVisible,
            isUpdate : false
        })
    }

    clickUpdateButton = (e) => {
        this.setState({
            isVisible: false,
            isUpdate: !this.state.isUpdate
        })
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name] : e.target.value
        })
    }

    updateProduct = (e) => {
        this.setState({
            isVisible:false,
            isUpdate:false
        })
        const{productID1, productName1, productDesc1, productCategory1, productPrice1} =this.state;

        const putProduct = {
            productID : productID1,
            productName : productName1,
            productDesc : productDesc1,
            productCategory : productCategory1,
            productPrice : productPrice1
        }

        const responce = axios.put('http://localhost:8080/product/update/'+productID1, putProduct);
    }

    clickDeleteIcon = (e) => {

        window.location.reload();

        return fetch('http://localhost:8080/product/delete/' + e.productID1, {
            method: 'DELETE',
        }).then(response => response.json())
    }

    render() {
        const {productID1, productName1, productDesc1, productCategory1, productPrice1, isVisible, isUpdate} = this.state;
        return (
            <div className="col-md-6 mb-4">
                <div className="card">
                    <div className="card-header d-flex justify-content-between" >
                        <h4 className="d-inline" onClick={this.headerClick}>{productName1}</h4>
                        <i className="far fa-trash-alt" style={{cursor: "pointer"}}
                           onClick={this.clickDeleteIcon.bind(this, {productID1})}></i>
                    </div>
                    {
                        isVisible ?
                            <div className="card-body">
                                <p className="card-text">Product ID : {productID1}</p>
                                <p className="card-text">Product Name : {productName1}</p>
                                <p className="card-text">Product Description : {productDesc1}</p>
                                <p className="card-text">Product Category : {productCategory1}</p>
                                <p className="card-text">Product Price : {productPrice1}</p>
                                <button className="btn btn-warning btn-block mb-2"
                                        onClick={this.clickUpdateButton}>Update Product
                                </button>
                            </div> :

                            isUpdate ?
                                <div className="card-body">
                                    <form>
                                        <div className="form-group">
                                            <label htmlFor="id">Product ID</label>
                                            <input type="number"
                                                   className="form-control"
                                                   placeholder={productID1}
                                                   name="id"
                                                   id="idUpdate"
                                                   value={productID1}
                                                   disabled={productID1}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="name">Product Name</label>
                                            <input type="text"
                                                   className="form-control"
                                                   name="productName1"
                                                   id="nameUpdate"
                                                   value={productName1}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="desc">Product Description</label>
                                            <input type="text"
                                                   className="form-control"
                                                   name="productDesc1"
                                                   id="descUpdate"
                                                   value={productDesc1}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="category">Product Category</label>
                                            <input type="text"
                                                   className="form-control"
                                                   name="productCategory1"
                                                   id="categoryUpdate"
                                                   value={productCategory1}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="price">Product Price</label>
                                            <input type="number"
                                                   className="form-control"
                                                   name="productPrice1"
                                                   id="priceUpdate"
                                                   value={productPrice1}
                                                   onChange={this.changeInput}
                                            />
                                        </div>
                                    </form>

                                    <button className="btn btn-warning btn-block"onClick={this.updateProduct}>Update</button>
                                </div> : null
                    }
                </div>
            </div>
        );
    }
}
Product.propTypes = {
    productName: PropTypes.string.isRequired,
    productDesc: PropTypes.string.isRequired,
    productCategory: PropTypes.string.isRequired,
    productPrice:PropTypes.number.isRequired
}

export default Product;