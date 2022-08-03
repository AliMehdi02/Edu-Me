import React,{useState} from 'react'
import { Link, useParams } from 'react-router-dom';
import './NavBar.css'
import About from "./About"
function NavBar() {
  
  const {id}=useParams()

    return (
    <div className='NavBar'>
        <nav className = "NavBar-items">
        <ul className="NavBar-links">
        <li> <Link to={`/about/${id}`}>About</Link></li>
        <li><Link to={`/matched-guides/${id}`}>Preferred Guides</Link></li>
         </ul>
        </nav>
 </div>
        
  )
}

export default NavBar