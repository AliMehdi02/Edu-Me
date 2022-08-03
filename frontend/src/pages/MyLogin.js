import React, {useState} from "react";
import {useNavigate} from 'react-router-dom';
import "./login/csstyle/style.scss";
import { Image } from 'react-bootstrap';
import  Forgotten from './login/Forgotten.js';
import {Link} from "react-router-dom";

export default function MyLogin() {

  
  const [UD, sUD] = useState({});
  const redirect = useNavigate()
  const handleChange = (e) => {
    sUD({ ...UD, [e.target.name]: e.target.value });
  };

  const handleClick = async (e) => {
    e.preventDefault();
    const res = await fetch('http://localhost:8080/loginn', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
            },
            body: JSON.stringify(UD)
        });
        res.json().then((data)=> {
          console.log(data);
          if(data.valid===true){
          localStorage.setItem('token', data.token);

          localStorage.setItem('UsersID from user table', data.userId);

          

          localStorage.setItem('CredentialsAuthorized', true);
          localStorage.setItem('isAuthenticated', true);
          redirect(`/view-guides-topics/${data.userId}`);
          }
          else{ 

         

            console.log('Wrong credentials Entered');

            localStorage.setItem('isAuthenticated', false);
          }  
        })
  };

  return (
    <>
    
      <div className="base-container">
      <div className="header">Login</div>
      <div className="content">
        <div className="image">
        <Image src='/login.png' height={200} />
        </div>
        <div className="form">
          <div className="form-group">
            <label htmlFor="username">Username</label>
            <input type="text" name="username" placeholder="username" 
            value={UD.username} onChange={handleChange} />
          </div>
          <div className="form-group">
            <label htmlFor="password">Password</label>
            <input type="password" name="password" placeholder="password" 
            value={UD.password} onChange={handleChange} />
          </div>
          <div>
             <Link to={'login/forgotten'} onClick={Forgotten}>Forgotten Password?</Link>  
            </div>
        </div>
      </div>
      <div className="footer">

        

        <button type="button" className="btn" onClick={handleClick}>
        
          Login
        </button>
      </div>
    </div>
    </>
  );
}