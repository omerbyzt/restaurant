import AddProduct from "./AddProduct";
import Table from 'react-bootstrap/Table'
import UpdateProduct from "./UpdateProduct";
import {Link} from 'react-router-dom'
import Header from "./Header";
import axios from "axios";
import UserContext from "../Context";
import React, {Component} from 'react';
import Loading from "./Loading";
import '../App.css';

class FirstPage extends Component {
    static contextType = UserContext;

    state = {
        content: [],
        isShowCard: "",
        isUpdateCard: "",
        obj: "",
        productTable: [],
        loadingIsVisible: false,
        totalCount: "",
        buttonArray: [],
        pageNumber:1,
        temp:""
    }

    constructor(props) {
        super(props);
        this.filterList = this.filterList.bind(this);
    }

    async componentDidMount() {
        this.setState({loadingIsVisible: true});
        const {token} = this.context

        if (localStorage.getItem("token") !== null || token !== "No Token") {
            const {setUserName, setToken} = this.context;
            setUserName(localStorage.getItem("username"));
            setToken(localStorage.getItem("token"));

            // let uri = "http://localhost:8080/product/listall";

            let uri = "http://localhost:8080/product/listmore";

            await fetch(uri, {
                method: 'get',
                headers: new Headers({
                    // 'Authorization': sessionStorage.getItem('token'),
                    'Authorization': token,
                }),
            })
                .then(response => response.json())
                .then(data => {
                    this.setState({
                        temp:data,
                        content: data.content,
                        totalCount: data.totalElements
                    })
                })


        } else {
            this.props.history.push('/');
        }
        this.setState({loadingIsVisible: false});

        // this.pageButtons();
    }

    clickedAddButton = () => {
        this.setState({
            isShowCard: !this.state.isShowCard
        })
    }

    onClickDeleteBtn = async (e) => {
        this.setState({loadingIsVisible: true});
        const {token} = this.context
        //delete için filtrelemesini yap
        //window.location.reload();
        await axios.delete('http://localhost:8080/product/delete/' + e.id,
            // {headers: {Authorization: sessionStorage.getItem('token')}});
            {headers: {Authorization: token}});
        // .then(res => {this.setState({content:this.state.content.filter(table => table.productID!==e.productID)})});
        this.setState({loadingIsVisible: false});
    }

    onClickUpdateBtn = (e) => {
        this.setState({
            obj: e,
            isUpdateCard: !this.state.isUpdateCard
        })
    }

    clickProductList = () => {
        this.setState({
            productTable: !this.state.productTable
        })
    }

    filterList = async (e) => {
        this.setState({loadingIsVisible: true});
        await this.setState({
            content: this.state.content.filter(
                productByFilter => productByFilter.productCategory == e
            )
        })
        this.setState({loadingIsVisible: false});
    }

    pageButtons = (e) => {
        // let count = Math.round(this.state.totalCount/10);
        // console.log("count : "+count);
        // for (let i = 0 ;i<count;i++){
        //     this.state.buttonArray.push(
        //         <button>
        //             <h4>{i}</h4>
        //         </button>
        //     )
        // }
        // console.log(this.state.buttonArray)
        // return this.state.buttonArray;
    }

    loadPage = async (e) => {
        const {token} = this.context
        this.setState({pageNumber: e+1})

        await axios.get('http://localhost:8080/product/listmore',
            {headers: {Authorization: token},
                params:{page:e,size:20}
            }
            )
            .then(res => {
                this.setState({content: res.data.content})
            });
    }

    render() {
        const {isShowCard, isUpdateCard, productTable, obj, content} = this.state;
        let buttonArray2 = [];
        let count = Math.round(this.state.totalCount / 20);
        for (let i = 0; i < count; i++) {
            if(this.state.pageNumber == i+1){
                    buttonArray2.push(
                        <button className="btn btn-primary ml-1" onClick={() => this.loadPage(i)}>
                            <h4>{i+1}</h4>
                        </button>)
            }else{
                buttonArray2.push(
                    <button className="btn btn-outline-primary ml-1" onClick={() => this.loadPage(i)}>
                        <h4>{i+1}</h4>
                    </button>
                )
            }

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
                            id={obj.id}
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
                                <th>Product Image</th>
                                <th>Product Categories</th>
                                <th>Buttons</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                content.map(v => {
                                    return (<tr align="left">
                                        <td align="center">{v.id}</td>
                                        <td>{v.productName}</td>
                                        <td>{v.productDesc}</td>
                                        <td>
                                            <button className="btn btn-link"
                                                    onClick={() => this.filterList(v.productCategory)}>{v.productCategory}</button>
                                        </td>
                                        <td>{v.productPrice}</td>
                                        <td align="center">
                                            <img src={'data:image/png;base64,' + v.mediaDTO.fileContent} width="100"
                                                 style={{margin: 10}}/>
                                        </td>
                                        <td>

                                            {
                                                v.categories.map(value => {
                                                    return (
                                                        <li>{value.name}</li>
                                                    )
                                                })
                                            }

                                        </td>
                                        <td align="center">
                                            <button className="btn btn-warning mr-2"
                                                    onClick={this.onClickUpdateBtn.bind(this, v)}>Update
                                            </button>
                                            <button className="btn btn-danger"
                                                    onClick={this.onClickDeleteBtn.bind(this, v)}>Delete
                                            </button>
                                        </td>
                                    </tr>)
                                })
                            }
                            </tbody>
                        </Table> : null
                }

                    <div align="center">
                        {
                            buttonArray2
                        }

                    </div>

                {
                    this.state.loadingIsVisible ?
                        <Loading/> : null
                }
            </div>
        );
    }
}

export default FirstPage;

