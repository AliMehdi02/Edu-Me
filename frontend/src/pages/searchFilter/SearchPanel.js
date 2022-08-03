import './styles/search-panel.css';
import {useState, useRef, useEffect} from 'react';
import Filter from './Filter';

function SearchPanel() {

    useEffect(() => {
        searchPanelRef.current.style = 'display: none';
    }, []);

    const [panelShowing, setPanelShowing] = useState(false);

    const searchPanelRef = useRef();
    const searchTextRef = useRef();

    function showHidePanel() {
        setPanelShowing(!panelShowing);
        if (panelShowing) {
            searchPanelRef.current.style = `transform: translate(-100%); 
            box-shadow: none;`;
            setTimeout(() => {
                searchPanelRef.current.style = 'display: none';
            }, 500);
        }
        else {
            searchPanelRef.current.style = 'display: block';
            setTimeout(() => {
                searchPanelRef.current.style = `transform: translate(0); 
                box-shadow: 5px 10px 20px 10px rgba(112, 112, 112, 0.336);`;
            }, 10);

        }
    }

    return (
        <>
        <div>
            <button className="search-show" onClick={() => showHidePanel()}>
                <i className="fas fa-search"></i>
            </button>
        </div>

        <div className='search-panel' ref={searchPanelRef}>
            <div className="hidebtn-container"><p className="hide-btn" title='test' 
                onClick={() => showHidePanel()}>&larr; Hide</p></div>
            
            <div className="search-bar">
                <input type='text' ref={searchTextRef} placeholder='Search...' className='search-bar-box'></input>
                    <button className='search-bar-btn' onClick={async () => {
                    const buildLink = window.location.href.split('/');
                    const builtLink = `http://${buildLink[2]}/search-results?` +
                    `q=${searchTextRef.current.value}`;
                    window.location.href = builtLink;
                }}><i className="fas fa-search"></i></button>
            </div>

            <Filter/>
        </div>
        </>
    );
}

export default SearchPanel;
