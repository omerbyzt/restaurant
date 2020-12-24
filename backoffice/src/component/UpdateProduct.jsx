import React, {Component} from 'react';
import axios from "axios";
import Header from "./Header";
import DropdownButton from "react-bootstrap/DropdownButton";
import Dropdown from "react-bootstrap/Dropdown";
import UserContext from "../Context";
import Loading from "./Loading";
import {Modal} from "react-bootstrap";

class UpdateProduct extends Component {
    static contextType = UserContext;
    state = {
        id: this.props.id,
        name: this.props.name,
        desc: this.props.desc,
        category: this.props.category,
        price: this.props.price,
        mediaList:[],
        selectedMediaName:"Select Media",
        selectedMediaID:"",
        selectedMediaURL:"",
        categoryList:[],
        selectedCategoryName:"",
        multiCategory: [],
        multiCategoryIds:[],
        loadingIsVisible:false,
        showModal: false,
    }

    async componentDidMount() {
        this.setState({loadingIsVisible: true});
        const {token} = this.context

        if (localStorage.getItem("token") !== null || token !== "No Token") {
            const {setUserName, setToken} = this.context;
            setUserName(localStorage.getItem("username"));
            setToken(localStorage.getItem("token"));

            await axios.get('http://localhost:8080/file/list',
                // {headers: {Authorization: sessionStorage.getItem('token')}})
                {headers: {Authorization: token}})
                .then(res => {
                    this.setState({mediaList: res.data})
                });

            await axios.get('http://localhost:8080/category/list-category',
                // {headers: {Authorization: sessionStorage.getItem('token')}})
                {headers: {Authorization: token}})
                .then(res => {
                    this.setState({categoryList: res.data})
                });
        } else {
            this.props.history.push('/');
        }
        this.setState({loadingIsVisible: false});
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name] : e.target.value
        })
    }

    updateProduct = async () => {
        this.setState({loadingIsVisible: true});
        const {token} = this.context
        const {id, name, desc, category, price, selectedMediaID, multiCategory, multiCategoryIds} = this.state;

        const newMedia = {
            id: selectedMediaID
        }

        const putProduct = {
            id: id,
            productName: name,
            productDesc: desc,
            productCategory: category,
            productPrice: price,
            mediaDTO: newMedia,
            categories: multiCategory,
            categoriesIds: multiCategoryIds
        }

        await axios.put('http://localhost:8080/product/update', putProduct,
            // {headers:{Authorization: sessionStorage.getItem('token')}});
            {headers: {Authorization: token}});

        console.log(putProduct);
        this.setState({loadingIsVisible: false});

        // eslint-disable-next-line no-restricted-globals
        event.preventDefault();
    }

    onClickMediaItem = (e) => {
        this.setState({
            selectedMediaName: e.name,
            selectedMediaID : e.id,
            selectedMediaURL:e.fileContent
        })
        console.log(e)
    }

    onClickCategoryItem = (e) => {
        this.setState({
            selectedCategoryName:e.name
        })
        this.state.multiCategory.push(e);
        this.state.multiCategoryIds.push(e.id)
    }

    handleModal = () => {
        this.setState({
            showModal: !this.state.showModal
        })
    }

    showMedia = () => {
        this.setState({
            showModal: !this.state.showModal
        })
    }

    render() {
        const {id, name, desc, category, price,mediaList,selectedMediaName,categoryList} = this.state;
        return (
            <div className="col-md-12 mb-4 mt-2">

                <Modal show={this.state.showModal} onHide={() => this.handleModal()}>
                    <Modal.Header closeButton><h4>{selectedMediaName}</h4></Modal.Header>
                    <Modal.Body align="center">
                        {
                            <img src={'data:image/png;base64,' + this.state.selectedMediaURL} width="250"
                                 style={{margin: 10}}/>
                        }
                    </Modal.Body>
                    <Modal.Footer>
                        <button className="btn btn-danger btn-block" onClick={() => this.handleModal()}>Close Modal
                        </button>
                    </Modal.Footer>
                </Modal>

                <div className="card" align="left">
                    <div className="card-header" align="center">
                        <h4>Update Product</h4>
                    </div>
                    <div className="card-body">
                        <form className="d-inline">
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

                            <div className="dropdown d-inline">
                                <label htmlFor="price">Product Media : </label>
                                <button className="btn btn-info dropdown-toggle dropdownCss" type="button"
                                        id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="true">
                                    {selectedMediaName}
                                </button>
                                <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    {
                                        mediaList.map(v => {
                                            return (
                                                <div className="row col-md -12">
                                                    <a className="dropdown-item"
                                                       onClick={this.onClickMediaItem.bind(this, v)}>
                                                        {v.name}
                                                        {/*<img src={'data:image/png;base64,' + v.fileContent} width="25"*/}
                                                    </a>
                                                </div>
                                            )
                                        })
                                    }
                                </div>
                            </div>
                        </form>
                        <button className="btn btn-link ml-2" onClick={() => this.showMedia()}>Show Media
                        </button>
                        <button className="btn btn-warning btn-block mt-3" onClick={this.updateProduct}>Update</button>
                    </div>
                </div>
                {
                    this.state.loadingIsVisible ?
                        <Loading/>:null
                }
            </div>
        );
    }
}

export default UpdateProduct;