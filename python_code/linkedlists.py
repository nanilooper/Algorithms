class ListNode:
    """
    a node in single linked List
    """

    def __init__(self, data=None, next=None):
            self.data = data
            self.next = next

    def __repr__(self):
        return self.data


class SingleLinkedList:
    """
    a single linked list implementation
    """

    def __init__(self):
        self.head = None

    def prepend(self,data):
        self.head = ListNode(data = data , next = self.head)

    def append(self,data):
        if not self.head:
            self.head = ListNode(data = data)
            return
        cur = self.head
        while cur.next:
            cur = cur.next
        cur.next = ListNode(data = data)

    def find(self,key):
        cur = self.head
        while cur and cur.data != key:
            cur = cur.next
        return cur

    def remove(self,key):
        cur = self.head
        prev = None
        while cur and cur.data != key:
            prev = cur
            cur = cur.next
        if prev == None:
            self.head = cur.next
        elif cur:
            prev.next = cur.next
            cur.next = None

    def reverse(self):
        cur = self.head
        prev_node = None
        next_node = None
        while cur:
            next_node = cur.next
            cur.next = prev_node
            prev_node = cur
            cur = next_node
        self.head = prev_node

    def __repr__(self):
        cur = self.head
        l = []
        while cur:
            l.append(cur.data)
            cur = cur.next
        return str(l)


class DListNode:
    """
    node for double linked List
    """

    def __init__(self, data=None, prev=None, next=None):
        self.data = data
        self.prev = prev
        self.next = next

    def __repr__(self):
        return self.data


class DoubleLinkedList:
    """
    double linked List
    """

    def __init__(self):
        self.head = None

    def prepend(self,data):
        new_head = DListNode(data=data,next=self.head)
        if self.head:
            self.head.prev = new_head
        self.head = new_head

    def append(self,data):
        if not self.head:
            self.head = DListNode(data=data)
            return
        cur = self.head
        while cur.next:
            cur = cur.next
        cur.next = DListNode(data=data,prev=cur)

    def find(self,key):
        cur = self.head
        while cur and cur.data != key:
            cur = cur.next
        return cur

    def remove_elem(self,node):
        if node.prev :
            node.prev.next = node.next
        if node.next:
            node.next.prev = node.prev
        if node is self.head:
            self.head = node.next
        node.prev = None
        node.next = None

    def remove(self,key):
        elem = self.find(key)
        if not elem:
            return
        self.remove_elem(elem)

    def reverse(self):
        cur = self.head
        prev_node = None
        while cur:
            prev_node = cur.prev
            next_node = cur.next
            cur.next = prev_node
            cur.prev = next_node
            cur = next_node
        self.head = prev_node.prev

    def __repr__(self):
        cur = self.head
        l = []
        while cur:
            l.append(cur.data)
            cur = cur.next
        return str(l)
