import React, {Component} from 'react';
import Header from "./Header";
import Table from "react-bootstrap/Table";
import UserContext from "../Context";
import Loading from "./Loading";
import axios from "axios";
import ReactToExcel from "react-html-table-to-excel";

class InfoList extends Component {
    static contextType = UserContext;
    state = {
        infoList: [],
        loadingIsVisible:false,
        beanList:[]
    }

    async componentDidMount() {
        this.setState({loadingIsVisible: true});
        const {token} = this.context

        if (localStorage.getItem("token") !== null || token !== "No Token") {
            const {setUserName, setToken} = this.context;
            setUserName(localStorage.getItem("username"));
            setToken(localStorage.getItem("token"));

            let uri = "http://localhost:8080/infolist";

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
                        infoList: data
                    })
                })

            axios.get('http://localhost:8080/all-bean',
                {headers:{Authorization: this.state.token}})
                .then(res => {this.setState({beanList: res.data})});
        } else {
            this.props.history.push('/');
        }
        this.setState({loadingIsVisible: false});
    }

    getBeansToExcel() {
        return <ReactToExcel
            className="btn btn-danger exportButton "
            table="bean-list"
            filename="bean-list"
            sheet="sheet 1"
            buttonText="Export Beans"
        />;
    }

    render() {
        const {infoList,beanList} = this.state;
        return (
            <div>
                <Header/>
                <Table striped bordered hover>
                    <thead>
                    <tr>
                        <th>Key</th>
                        <th>Value</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        infoList.map(v => {
                            return (
                                <tr>
                                    <td>{v.key}</td>
                                    <td>{v.value}</td>
                                </tr>
                            )
                        })
                    }

                    </tbody>
                </Table>

                <div align="right">
                    {this.getBeansToExcel()}
                </div>

                <Table striped bordered hover id="bean-list" className="mt-3">
                    <thead>
                    <tr>
                        <th>Index</th>
                        <th>Bean Name</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        beanList.map((v, index) => {
                            return (
                                <tr>
                                    <td>{index + 1}</td>
                                    <td>{v}</td>
                                </tr>
                            )
                        })
                    }

                    </tbody>
                </Table>

                {
                    this.state.loadingIsVisible ?
                        <Loading/> : null
                }
            </div>
        );
    }
}

export default InfoList;