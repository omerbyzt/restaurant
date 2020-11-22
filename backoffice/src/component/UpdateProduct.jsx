import React, {Component} from 'react';
import axios from "axios";
import Header from "./Header";

class UpdateProduct extends Component {
    state = {
        id: this.props.id,
        name: this.props.name,
        desc: this.props.desc,
        category: this.props.category,
        price: this.props.price
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name] : e.target.value
        })
    }

    updateProduct = () => {
        const {id,name,desc,category,price} = this.state;

        const putProduct ={
            productID : id,
            productName : name,
            productDesc : desc,
            productCategory : category,
            productPrice : price
        }

        axios.put('http://localhost:8080/product/update/'+id,putProduct,
            {headers:{Authorization:'Basic '+btoa('admin:pass3')}});
    }

    render() {
        const {id, name, desc, category, price} = this.state;
        return (
            <div className="col-md-12 mb-4 mt-2">
                <div className="card" align="left">
                    <div className="card-header" align="center">
                        <h4>Update Product</h4>
                    </div>
                    <div className="card-body">
                        <form>
                            <div className="form-group">
                                <label htmlFor="id">Product ID</label>
                                <input type="number"
                                       className="form-control"
                                       placeholder={id}
                                       name="id"
                                       id="nameInput"
                                       value={id}
                                       disabled={id}
                                       onChange={this.changeInput}
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="name">Product Name</label>
                                <input type="text"
                                       className="form-control"
                                       placeholder={name}
                                       name="name"
                                       id="descInput"
                                       value={name}
                                       onChange={this.changeInput}
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="desc">Product Description</label>
                                <input type="text"
                                       className="form-control"
                                       placeholder={desc}
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
                                       placeholder={category}
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
                                       placeholder={price}
                                       name="price"
                                       id="priceInput"
                                       value={price}
                                       onChange={this.changeInput}
                                />
                            </div>
                            <button className="btn btn-warning btn-block" onClick={this.updateProduct}>Update</button>
                        </form>
                    </div>
                </div>

            </div>
        );
    }
}

export default UpdateProduct;