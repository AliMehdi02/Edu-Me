
const toggleSearchLoadingReducer = (state = false, action) => {
    switch (action.type) {
        case 'LOADING_TOGGLE':
            return (!state);
        default:
            return state;
    }
};

export default toggleSearchLoadingReducer;
