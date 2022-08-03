import Preferences from './pages/preferences/Preferences';
import {Routes, Route, BrowserRouter as Router} from 'react-router-dom';
import Blank from './pages/Blank';
import CreateGuide from './pages/guides/create-guide-components/CreateGuide';
import Guides from './pages/guides/Guides';
import MyLogin from './pages/MyLogin';
import ViewGuide from './pages/guides/ViewGuide';
import ProtectedRoute from './ProtectedRoute';
import './App.css';
import { useState } from 'react';
import SearchPanel from './pages/searchFilter/SearchPanel';
import SearchResults from './pages/searchFilter/searchResults/SearchResults';
import { Login } from './pages/login/login';
import UserGuidesPage from './pages/guides/user-guides/UserGuidesPage';
import AllMatches from './pages/userMatches/AllMatches';
import  Forgotten from './pages/login/Forgotten.js';
import Findlike from './pages/mostlikes/Findlike';

import  Reset from './pages/login/Reset.js';
import { useParams } from 'react-router-dom';
import About from './pages/matching/About';
import MatchingGuides from './pages/matching/MatchingGuides';
import CreateGuideF from './pages/guides/create-guide-components/CreateGuideF';
import Navbarr from './pages/Navbar/Indexx';

function App() {
  const [showSearchPanel, setShowSearchPanel] = useState(true);
  // const []
  
  return (
    <>
   
    <Router>
      <Routes>
        <Route path='/' exact element={<MyLogin/>}></Route>
        <Route path='/login' exact element={<MyLogin/>}></Route>
        {/* <Route path='/loginn' exact element={<Login/>}></Route> */}
    
        <Route path = '/copyg'exact element={<CreateGuideF/>}></Route>
        <Route path='/login/forgotten' element={<Forgotten/>}></Route>
        <Route path='/reset/password/:id' element={<Reset/>}></Route>
        <Route exact path='/setup' element={<ProtectedRoute/>}>
            <Route exact path='/setup' element={<Preferences/>}/>
        </Route>
        <Route exact path='/search-results' element={<ProtectedRoute/>}>
            <Route exact path='/search-results' element={<SearchResults/>}/>
        </Route>
        <Route exact path='/create-new-guide' element={<ProtectedRoute/>}>
            <Route exact path='/create-new-guide' element={<CreateGuide/>}/>
        </Route>
        <Route exact path='/edit-guide' element={<ProtectedRoute/>}>
            <Route exact path='/edit-guide' element={<CreateGuide/>}/>
        </Route>
        <Route exact path='/guides' element={<ProtectedRoute/>}>
            <Route exact path='/guides' element={<Guides/>}/>
        </Route>
        <Route exact path="/view-guides-topics/:user_id" element={<ProtectedRoute/>}>
            <Route exact path="/view-guides-topics/:user_id" element={<Guides/>}/>
        </Route> 
        <Route exact path='/view-guide' element={<ProtectedRoute/>}>
            <Route exact path='/view-guide' element={<ViewGuide/>}/>
        </Route> 
        <Route exact path='/my-guides' element={<ProtectedRoute/>}>
          <Route exact path='/my-guides' element={<UserGuidesPage/>}/>
        </Route>
        <Route exact path='/matches/:id' element={<ProtectedRoute/>}>
          <Route exact path='/matches/:id' element={<AllMatches/>}/>
        </Route>
           <Route exact path='/about/:id' element={<ProtectedRoute/>}>
            <Route exact path='/about/:id' element={<About/>}/>
        </Route>
        <Route exact path='/matched-guides/:id' element={<ProtectedRoute/>}>
            <Route exact path='/matched-guides/:id' element={<MatchingGuides/>}/>
        </Route>
        <Route exact path='/findlike' element={<ProtectedRoute/>}>
            <Route exact path='/findlike' element={<Findlike/>}/>
        </Route> 
        
        <Route exact path='/blank' element={<Blank/>}/>
      </Routes>
    </Router>
    </>
  );
}

export default App;
