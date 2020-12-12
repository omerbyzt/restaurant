import React, {Component} from 'react';
import axios from "axios";
import Header from "./Header";
import DropdownButton from "react-bootstrap/DropdownButton";
import Dropdown from "react-bootstrap/Dropdown";

class UpdateProduct extends Component {
    state = {
        id: this.props.id,
        name: this.props.name,
        desc: this.props.desc,
        category: this.props.category,
        price: this.props.price,
        mediaList:[],
        selectedMediaName:"Select Media",
        selectedMediaID:"",
        categoryList:[],
        selectedCategoryName:"",
        multiCategory: [],
        multiCategoryIds:[]
    }

    componentDidMount() {
        axios.get('http://localhost:8080/file/list',
            {headers: {Authorization: sessionStorage.getItem('token')}})
            .then(res => {
                this.setState({mediaList: res.data})
            });

        axios.get('http://localhost:8080/category/list-category',
            {headers: {Authorization: sessionStorage.getItem('token')}})
            .then(res => {
                this.setState({categoryList: res.data})
            });
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name] : e.target.value
        })
    }

    updateProduct = () => {
        const {id,name,desc,category,price,selectedMediaID,multiCategory,multiCategoryIds} = this.state;

        const newMedia = {
            id:selectedMediaID
        }

        const putProduct ={
            productID : id,
            productName : name,
            productDesc : desc,
            productCategory : category,
            productPrice : price,
            mediaDTO : newMedia,
            categories:multiCategory,
            categoriesIds:multiCategoryIds
        }

        axios.put('http://localhost:8080/product/update',putProduct,
            {headers:{Authorization: sessionStorage.getItem('token')}});

        console.log(putProduct);

        // eslint-disable-next-line no-restricted-globals
        event.preventDefault();
    }

    onClickMediaItem = (e) => {
        this.setState({
            selectedMediaName: e.name,
            selectedMediaID : e.id
        })
    }

    onClickCategoryItem = (e) => {
        this.setState({
            selectedCategoryName:e.name
        })
        this.state.multiCategory.push(e);
        this.state.multiCategoryIds.push(e.id)
        console.log(this.state.multiCategory)
    }

    render() {
        const {id, name, desc, category, price,mediaList,selectedMediaName,categoryList} = this.state;
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
                                       disabled = {category}
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

                            <div className="dropdown">
                                <label htmlFor="price">Product Category : </label>
                                <button className="btn btn-info dropdown-toggle dropdownCss" type="button"
                                        id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="true">
                                    Deneme
                                </button>
                                <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    {
                                        categoryList.map(v => {
                                            return (

                                                <div className="row col-md -12">
                                                    <label>
                                                        <input type="checkbox" value=""
                                                               onClick={this.onClickCategoryItem.bind(this, v)}
                                                        />{v.name}
                                                    </label>
                                                </div>

                                            )
                                        })
                                    }
                                </div>
                            </div>

                            <DropdownButton id="dropdown-basic-button" title={selectedMediaName} className="mt-3">
                                {
                                    mediaList.map(v => {
                                        return (
                                            <Dropdown.Item
                                                onClick={this.onClickMediaItem.bind(this, v)}
                                            >{v.name}</Dropdown.Item>
                                        )
                                    })
                                }
                            </DropdownButton>

                            <button className="btn btn-warning btn-block mt-3" onClick={this.updateProduct}>Update</button>
                        </form>
                    </div>
                </div>

            </div>
        );
    }
}

export default UpdateProduct;