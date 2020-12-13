import React, {Component} from 'react';

const UserContext = React.createContext();

export class UserProvider extends Component {

    state = {
        token:"No Token",
        username:"No User"
    }

    setToken = (token) => {
        this.setState((prevState) => ({token}))
    }
    setUserName = (username) => {
        this.setState((prevState) => ({username}))
    }

    render() {
        const{token,username}=this.state;
        const{setToken,setUserName}=this;
        return (
            <UserContext.Provider value={{token,setToken,username,setUserName}}>
                {this.props.children}
            </UserContext.Provider>
        );
    }
}
export default UserContext;