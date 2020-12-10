import React, {Component} from 'react';

const UserContext = React.createContext();

export class UserProvider extends Component {

    state = {
        token:"No Token",
        username:"No User",
        waiterID:-1,
        waiterName:"No Waiter",
        table:"No Table"
    }

    setToken = (token) => {
        this.setState((prevState) => ({token}))
    }
    setUserName = (username) => {
        this.setState((prevState) => ({username}))
    }
    setWaiterID = (waiterID) => {
        this.setState((prevState) => ({waiterID}))
    }
    setWaiterName = (waiterName) => {
        this.setState((prevState) => ({waiterName}))
    }
    setTable = (table) => {
        this.setState((prevState) => ({table}))
    }

    render() {
        const{token,username,waiterID,waiterName,table}=this.state;
        const{setToken,setUserName,setWaiterID,setWaiterName,setTable}=this;
        return (
            <UserContext.Provider value={{token,setToken,username,setUserName,waiterID,setWaiterID,waiterName,setWaiterName,table,setTable}}>
                {this.props.children}
            </UserContext.Provider>
        );
    }
}
export default UserContext;