import React from 'react';
import logo from './NavIcon.svg';



import {
  Nav,
  NavLink,
  Bars,
  NavMenu,
  NavBtn,
  NavBtnLinks
} from './NavbarElems.js';


const handleLogout= ()=>{
  localStorage.clear();
  localStorage.setItem('isAuthenticated', false);
  
}


const Navbarr = () => {
  return (
    <>
    
      <Nav>
      <img src={logo}/>
        <NavLink to='/'>
        
        </NavLink>
        <Bars />
        <NavMenu>
        <NavLink to='/view-guides-topics/1' activeStyle>
            Home
          </NavLink>
          <NavLink to='/my-guides' activeStyle>
            My Guides
          </NavLink>
          <NavLink to='/create-new-guide' activeStyle>
            Create A Guide
          </NavLink>
          <NavLink to='/findlike' activeStyle>
            Most Popular Guides
          </NavLink>
          {/* <NavLink to='/setup' activeStyle>
            Setup A Guide
          </NavLink> */}
        
        </NavMenu>
        <NavBtn>
          <NavBtnLinks to='/login' onClick={handleLogout}>Logout</NavBtnLinks>
        </NavBtn>
      </Nav>
    </>
  );
};

export default Navbarr;