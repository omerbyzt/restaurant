import React, {Component} from 'react';
import Header from "./Header";
import axios from "axios";

class AddTable extends Component {
    state = {
        tableCategoryList:[],
        number:"",
        category:"Select Category",
        categoryId:""
    }
    componentDidMount() {
        const{tableCategoryList} = this.state

        axios.get('http://localhost:8080/table-category/listall',
            {headers:{Authorization: sessionStorage.getItem('token')}})
            .then(res => {this.setState({tableCategoryList: res.data})});
            console.log(tableCategoryList)
    }

    onClickItem = (e) => {
        this.setState({
            category:e.name,
            categoryId:e.id
        })
    }

    addTable = () => {
        const{number,category,categoryId}=this.state

        const newTable = {
            number:number,
            category:category
        }

        axios.post("http://localhost:8080/table/add/"+categoryId,newTable,
            {headers:{Authorization:sessionStorage.getItem('token')}})
            .then(res => {this.setState({tableCategoryList:this.state.tableCategoryList})});
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    render() {
        const{number,tableCategoryList,category} = this.state
        return (
            <div>
                <Header/>
                    <div className="col-md-12 mb-4 mt-4">
                        <div className="card">
                            <div className="card-header">
                                <h4 className="d-inline">Add Table</h4>
                            </div>
                            <div className="card-body">
                                <form>

                                    <div className="form-group">
                                        <label htmlFor="number">Table Number</label>
                                        <input type="number"
                                               className="form-control"
                                               placeholder="Enter Product Name"
                                               name="number"
                                               id="numberInput"
                                               value={number}
                                               onChange={this.changeInput}
                                        />
                                    </div>

                                    <div className="dropdown">
                                        <label htmlFor="price">Product Category : </label>
                                        <button className="btn btn-info dropdown-toggle dropdownCss" type="button"
                                                id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                                aria-expanded="true">
                                            {category}
                                        </button>
                                        <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                            {
                                                tableCategoryList.map(v=>{
                                                    return(
                                                        <a className="dropdown-item" onClick={this.onClickItem.bind(this,v)}>{v.name}</a>
                                                    )
                                                })
                                            }
                                        </div>
                                    </div>

                                    <button className="btn btn-warning btn-block addProductButtonCss" onClick={this.addTable}>Add Table</button>

                                </form>
                            </div>
                        </div>
                    </div>


            </div>
        );
    }
}

export default AddTable;